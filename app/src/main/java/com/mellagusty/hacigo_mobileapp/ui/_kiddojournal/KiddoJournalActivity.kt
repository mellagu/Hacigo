package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.KiddoJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoJournalBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class KiddoJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoJournalBinding
    private lateinit var kiddoJournalAdapter: KiddoJournalAdapter
    private lateinit var viewModel: KiddoJournalViewModel
    private var noteId = -1

    private var list: List<KiddoJournalEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKiddoJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(KiddoJournalViewModel::class.java)


        kiddoJournalAdapter = KiddoJournalAdapter {
            val intent = Intent(this, CreateJournalActivity::class.java)
            intent.putExtra("note_id", it.id)
            startActivity(intent)
        }


        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this, CreateJournalActivity::class.java)
            intent.putExtra("note_id",-1)
            startActivity(intent)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerView.adapter = kiddoJournalAdapter


        Log.d("linechart", "linechart sebelum observe")
        viewModel.lineDataSet.observe(this) { lineDataSet ->
            styleLineDataSet(lineDataSet)
            binding.lineChart.data = LineData(lineDataSet)
            binding.lineChart.invalidate()
            Log.d("linechart","linechart dalam observe")
        }
        styleChartKiddo(binding.lineChart)
        viewModel.getJournalData()

        getKiddoInfo()

        searchBar()
        getAllJournal()

    }

    private fun searchBar() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tempArray = ArrayList<KiddoJournalEntity>()
                for (array in list ){
                    if (array.title!!.toLowerCase(Locale.getDefault()).contains(newText.toString())){
                        tempArray.add(array)
                    }
                }
                kiddoJournalAdapter.setListData(tempArray)
                kiddoJournalAdapter.notifyDataSetChanged()
                return true
            }
        })
    }


    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = viewModel.getJournalAll()

            kiddoJournalAdapter.setListData(journal)
            list = journal
            binding.recyclerView.adapter = kiddoJournalAdapter
        }
    }

    private fun getKiddoInfo() {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(KiddoJournalViewModel::class.java)


//      yang akan ditampilkan : bb terakhir, tb terakhir, keterangan sudah ideal atau belum, rekomendasi berat ideal

        CoroutineScope(Dispatchers.IO).launch {
            val kiddoInfo = viewModel.getLastJournal()
            if ( kiddoInfo != null ) {
                Log.d("kiddo", kiddoInfo.toString() )

                val resultIdeal: ArrayList<String>
                resultIdeal = dataIdeal(kiddoInfo)
                Log.d( "kiddo", "hasilnya adalah $resultIdeal" )

                binding.tvStatusideal.setText(resultIdeal[0])
                binding.tvRentangideal.setText(resultIdeal[1])
            }
            else {
                Log.d( "kiddo", "belum ada data, silahkan input data" )



            }
        }
    }

    private fun dataIdeal( kiddoInfo: KiddoJournalEntity): ArrayList<String> {
        var resultIdeal = arrayListOf<String>()
        val gender: String? = "P"
//        var gender: String? = kiddoInfo.gender
        val tb: Int? = kiddoInfo.height?.toInt()
        val bb: Int? = kiddoInfo.weight?.toInt()
        val usia: Int? = kiddoInfo.ageInMonth?.toInt()

        var idealStatus: String? = "Berat badan anak belum ideal"
        var rentang: String? = "rentang"

        val dataIdealP: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 43.6, 45.4, 47.3, 49.1, 51.0, 52.9, 54.7 ),
            doubleArrayOf( 1.0, 47.8, 49.8, 51.7, 53.7, 55.6, 57.6, 59.5 ),
            doubleArrayOf( 2.0, 51.0, 53.0, 55.0, 57.1, 59.1, 61.1, 63.2 ),
            doubleArrayOf( 3.0, 53.5, 55.6, 57.7, 59.8, 61.9, 64.0, 66.1 ),
            doubleArrayOf( 4.0, 55.6, 57.8, 59.9, 62.1, 64.3, 66.4, 68.6 ),
            doubleArrayOf( 5.0, 57.4, 59.6, 61.8, 64.0, 66.2, 68.5, 70.7 ),
            doubleArrayOf( 6.0, 58.9, 61.2, 63.5, 65.7, 68.0, 70.3, 72.5 ),
            doubleArrayOf( 7.0, 60.3, 62.7, 65.0, 67.3, 69.6, 71.9, 74.2 ),
            doubleArrayOf( 8.0, 61.7, 64.0, 66.4, 68.7, 71.1, 73.5, 75.8 ),
            doubleArrayOf( 9.0, 62.9, 65.3, 67.7, 70.1, 72.6, 75.0, 77.4 ),
            doubleArrayOf( 10.0, 64.1, 66.5, 69.0, 71.5, 73.9, 76.4, 78.9 ),
            doubleArrayOf( 11.0, 65.2, 67.7, 70.3, 72.8, 75.3, 77.8, 80.3 ),
            doubleArrayOf( 12.0, 66.3, 68.9, 71.4, 74.0, 76.6, 79.2, 81.7 ),
            doubleArrayOf( 13.0, 67.3, 70.0, 72.6, 75.2, 77.8, 80.5, 83.1 ),
            doubleArrayOf( 14.0, 68.3, 71.0, 73.7, 76.4, 79.1, 81.7, 84.4 ),
            doubleArrayOf( 15.0, 69.3, 72.0, 74.8, 77.5, 80.2, 83.0, 85.7 ),
            doubleArrayOf( 16.0, 70.2, 73.0, 75.8, 78.6, 81.4, 84.2, 87.0 ),
            doubleArrayOf( 17.0, 71.1, 74.0, 76.8, 79.7, 82.5, 85.4, 88.2 ),
            doubleArrayOf( 18.0, 72.0, 74.9, 77.8, 80.7, 83.6, 86.5, 89.4 ),
            doubleArrayOf( 19.0, 72.8, 75.8, 78.8, 81.7, 84.7, 87.6, 90.6 ),
            doubleArrayOf( 20.0, 73.7, 76.7, 79.7, 82.7, 85.7, 88.7, 91.7 ),
            doubleArrayOf( 21.0, 74.5, 77.5, 80.6, 83.7, 86.7, 89.8, 92.9 ),
            doubleArrayOf( 22.0, 75.2, 78.4, 81.5, 84.6, 87.7, 90.8, 94.0 ),
            doubleArrayOf( 23.0, 76.0, 79.2, 82.3, 85.5, 88.7, 91.9, 95.0 ),
            doubleArrayOf( 24.0, 76.0, 79.3, 82.5, 85.7, 88.9, 92.2, 95.4 ),
            doubleArrayOf( 25.0, 76.8, 80.0, 83.3, 86.6, 89.9, 93.1, 96.4 ),
            doubleArrayOf( 26.0, 77.5, 80.8, 84.1, 87.4, 90.8, 94.1, 97.4 ),
            doubleArrayOf( 27.0, 78.1, 81.5, 84.9, 88.3, 91.7, 95.0, 98.4 ),
            doubleArrayOf( 28.0, 78.8, 82.2, 85.7, 89.1, 92.5, 96.0, 99.4 ),
            doubleArrayOf( 29.0, 79.5, 82.9, 86.4, 89.9, 93.4, 96.9, 100.3 ),
            doubleArrayOf( 30.0, 80.1, 83.6, 87.1, 90.7, 94.2, 97.7, 101.3 ),
            doubleArrayOf( 31.0, 80.7, 84.3, 87.9, 91.4, 95.0, 98.6, 102.2 ),
            doubleArrayOf( 32.0, 81.3, 84.9, 88.6, 92.2, 95.8, 99.4, 103.1 ),
            doubleArrayOf( 33.0, 81.9, 85.6, 89.3, 92.9, 96.6, 100.3, 103.9 ),
            doubleArrayOf( 34.0, 82.5, 86.2, 89.9, 93.6, 97.4, 101.1, 104.8 ),
            doubleArrayOf( 35.0, 83.1, 86.8, 90.6, 94.4, 98.1, 101.9, 105.6 ),
            doubleArrayOf( 36.0, 83.6, 87.4, 91.2, 95.1, 98.9, 102.7, 106.5 ),
            doubleArrayOf( 37.0, 84.2, 88.0, 91.9, 95.7, 99.6, 103.4, 107.3 ),
            doubleArrayOf( 38.0, 84.7, 88.6, 92.5, 96.4, 100.3, 104.2, 108.1 ),
            doubleArrayOf( 39.0, 85.3, 89.2, 93.1, 97.1, 101.0, 105.0, 108.9 ),
            doubleArrayOf( 40.0, 85.8, 89.8, 93.8, 97.7, 101.7, 105.7, 109.7 ),
            doubleArrayOf( 41.0, 86.3, 90.4, 94.4, 98.4, 102.4, 106.4, 110.5 ),
            doubleArrayOf( 42.0, 86.8, 90.9, 95.0, 99.0, 103.1, 107.2, 111.2 ),
            doubleArrayOf( 43.0, 87.4, 91.5, 95.6, 99.7, 103.8, 107.9, 112.0 ),
            doubleArrayOf( 44.0, 87.9, 92.0, 96.2, 100.3, 104.5, 108.6, 112.7 ),
            doubleArrayOf( 45.0, 88.4, 92.5, 96.7, 100.9, 105.1, 109.3, 113.5 ),
            doubleArrayOf( 46.0, 88.9, 93.1, 97.3, 101.5, 105.8, 110.0, 114.2 ),
            doubleArrayOf( 47.0, 89.3, 93.6, 97.9, 102.1, 106.4, 110.7, 114.9 ),
            doubleArrayOf( 48.0, 89.8, 94.1, 98.4, 102.7, 107.0, 111.3, 115.7 ),
            doubleArrayOf( 49.0, 90.3, 94.6, 99.0, 103.3, 107.7, 112.0, 116.4 ),
            doubleArrayOf( 50.0, 90.7, 95.1, 99.5, 103.9, 108.3, 112.7, 117.1 ),
            doubleArrayOf( 51.0, 91.2, 95.6, 100.1, 104.5, 108.9, 113.3, 117.7 ),
            doubleArrayOf( 52.0, 91.7, 96.1, 100.6, 105.0, 109.5, 114.0, 118.4 ),
            doubleArrayOf( 53.0, 92.1, 96.6, 101.1, 105.6, 110.1, 114.6, 119.1 ),
            doubleArrayOf( 54.0, 92.6, 97.1, 101.6, 106.2, 110.7, 115.2, 119.8 ),
            doubleArrayOf( 55.0, 93.0, 97.6, 102.2, 106.7, 111.3, 115.9, 120.4 ),
            doubleArrayOf( 56.0, 93.4, 98.1, 102.7, 107.3, 111.9, 116.5, 121.1 ),
            doubleArrayOf( 57.0, 93.9, 98.5, 103.2, 107.8, 112.5, 117.1, 121.8 ),
            doubleArrayOf( 58.0, 94.3, 99.0, 103.7, 108.4, 113.0, 117.7, 122.4 ),
            doubleArrayOf( 59.0, 94.7, 99.5, 104.2, 108.9, 113.6, 118.3, 123.1 ),
            doubleArrayOf( 60.0, 95.2, 99.9, 104.7, 109.4, 114.2, 118.9, 123.7 )
        )

        val dataIdealL: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 44.2, 46.1, 48.0, 49.9, 51.8, 53.7, 55.6 ),
            doubleArrayOf( 1.0, 48.9, 50.8, 52.8, 54.7, 56.7, 58.6, 60.6 ),
            doubleArrayOf( 2.0, 52.4, 54.4, 56.4, 58.4, 60.4, 62.4, 64.4 ),
            doubleArrayOf( 3.0, 55.3, 57.3, 59.4, 61.4, 63.5, 65.5, 67.6 ),
            doubleArrayOf( 4.0, 57.6, 59.7, 61.8, 63.9, 66.0, 68.0, 70.1 ),
            doubleArrayOf( 5.0, 59.6, 61.7, 63.8, 65.9, 68.0, 70.1, 72.2 ),
            doubleArrayOf( 6.0, 61.2, 63.3, 65.5, 67.6, 69.8, 71.9, 74.0 ),
            doubleArrayOf( 7.0, 62.7, 64.8, 67.0, 69.2, 71.3, 73.5, 75.7 ),
            doubleArrayOf( 8.0, 64.0, 66.2, 68.4, 70.6, 72.8, 75.0, 77.2 ),
            doubleArrayOf( 9.0, 65.2, 67.5, 69.7, 72.0, 74.2, 76.5, 78.7 ),
            doubleArrayOf( 10.0, 66.4, 68.7, 71.0, 73.3, 75.6, 77.9, 80.1 ),
            doubleArrayOf( 11.0, 67.6, 69.9, 72.2, 74.5, 76.9, 79.2, 81.5 ),
            doubleArrayOf( 12.0, 68.6, 71.0, 73.4, 75.7, 78.1, 80.5, 82.9 ),
            doubleArrayOf( 13.0, 69.6, 72.1, 74.5, 76.9, 79.3, 81.8, 84.2 ),
            doubleArrayOf( 14.0, 70.6, 73.1, 75.6, 78.0, 80.5, 83.0, 85.5 ),
            doubleArrayOf( 15.0, 71.6, 74.1, 76.6, 79.1, 81.7, 84.2, 86.7 ),
            doubleArrayOf( 16.0, 72.5, 75.0, 77.6, 80.2, 82.8, 85.4, 88.0 ),
            doubleArrayOf( 17.0, 73.3, 76.0, 78.6, 81.2, 83.9, 86.5, 89.2 ),
            doubleArrayOf( 18.0, 74.2, 76.9, 79.6, 82.3, 85.0, 87.7, 90.4 ),
            doubleArrayOf( 19.0, 75.0, 77.7, 80.5, 83.2, 86.0, 88.8, 91.5 ),
            doubleArrayOf( 20.0, 75.8, 78.6, 81.4, 84.2, 87.0, 89.8, 92.6 ),
            doubleArrayOf( 21.0, 76.5, 79.4, 82.3, 85.1, 88.0, 90.9, 93.8 ),
            doubleArrayOf( 22.0, 77.2, 80.2, 83.1, 86.0, 89.0, 91.9, 94.9 ),
            doubleArrayOf( 23.0, 78.0, 81.0, 83.9, 86.9, 89.9, 92.9, 95.9 ),
            doubleArrayOf( 24.0, 78.0, 81.0, 84.1, 87.1, 90.2, 93.2, 96.3 ),
            doubleArrayOf( 25.0, 78.6, 81.7, 84.9, 88.0, 91.1, 94.2, 97.3 ),
            doubleArrayOf( 26.0, 79.3, 82.5, 85.6, 88.8, 92.0, 95.2, 98.3 ),
            doubleArrayOf( 27.0, 79.9, 83.1, 86.4, 89.6, 92.9, 96.1, 99.3 ),
            doubleArrayOf( 28.0, 80.5, 83.8, 87.1, 90.4, 93.7, 97.0, 100.3 ),
            doubleArrayOf( 29.0, 81.1, 84.5, 87.8, 91.2, 94.5, 97.9, 101.2 ),
            doubleArrayOf( 30.0, 81.7, 85.1, 88.5, 91.9, 95.3, 98.7, 102.1 ),
            doubleArrayOf( 31.0, 82.3, 85.7, 89.2, 92.7, 96.1, 99.6, 103.0 ),
            doubleArrayOf( 32.0, 82.8, 86.4, 89.9, 93.4, 96.9, 100.4, 103.9 ),
            doubleArrayOf( 33.0, 83.4, 86.9, 90.5, 94.1, 97.6, 101.2, 104.8 ),
            doubleArrayOf( 34.0, 83.9, 87.5, 91.1, 94.8, 98.4, 102.0, 105.6 ),
            doubleArrayOf( 35.0, 84.4, 88.1, 91.8, 95.4, 99.1, 102.7, 106.4 ),
            doubleArrayOf( 36.0, 85.0, 88.7, 92.4, 96.1, 99.8, 103.5, 107.2 ),
            doubleArrayOf( 37.0, 85.5, 89.2, 93.0, 96.7, 100.5, 104.2, 108.0 ),
            doubleArrayOf( 38.0, 86.0, 89.8, 93.6, 97.4, 101.2, 105.0, 108.8 ),
            doubleArrayOf( 39.0, 86.5, 90.3, 94.2, 98.0, 101.8, 105.7, 109.5 ),
            doubleArrayOf( 40.0, 87.0, 90.9, 94.7, 98.6, 102.5, 106.4, 110.3 ),
            doubleArrayOf( 41.0, 87.5, 91.4, 95.3, 99.2, 103.2, 107.1, 111.0 ),
            doubleArrayOf( 42.0, 88.0, 91.9, 95.9, 99.9, 103.8, 107.8, 111.7 ),
            doubleArrayOf( 43.0, 88.4, 92.4, 96.4, 100.4, 104.5, 108.5, 112.5 ),
            doubleArrayOf( 44.0, 88.9, 93.0, 97.0, 101.0, 105.1, 109.1, 113.2 ),
            doubleArrayOf( 45.0, 89.4, 93.5, 97.5, 101.6, 105.7, 109.8, 113.9 ),
            doubleArrayOf( 46.0, 89.8, 94.0, 98.1, 102.2, 106.3, 110.4, 114.6 ),
            doubleArrayOf( 47.0, 90.3, 94.4, 98.6, 102.8, 106.9, 111.1, 115.2 ),
            doubleArrayOf( 48.0, 90.7, 94.9, 99.1, 103.3, 107.5, 111.7, 115.9 ),
            doubleArrayOf( 49.0, 91.2, 95.4, 99.7, 103.9, 108.1, 112.4, 116.6 ),
            doubleArrayOf( 50.0, 91.6, 95.9, 100.2, 104.4, 108.7, 113.0, 117.3 ),
            doubleArrayOf( 51.0, 92.1, 96.4, 100.7, 105.0, 109.3, 113.6, 117.9 ),
            doubleArrayOf( 52.0, 92.5, 96.9, 101.2, 105.6, 109.9, 114.2, 118.6 ),
            doubleArrayOf( 53.0, 93.0, 97.4, 101.7, 106.1, 110.5, 114.9, 119.2 ),
            doubleArrayOf( 54.0, 93.4, 97.8, 102.3, 106.7, 111.1, 115.5, 119.9 ),
            doubleArrayOf( 55.0, 93.9, 98.3, 102.8, 107.2, 111.7, 116.1, 120.6 ),
            doubleArrayOf( 56.0, 94.3, 98.8, 103.3, 107.8, 112.3, 116.7, 121.2 ),
            doubleArrayOf( 57.0, 94.7, 99.3, 103.8, 108.3, 112.8, 117.4, 121.9 ),
            doubleArrayOf( 58.0, 95.2, 99.7, 104.3, 108.9, 113.4, 118.0, 122.6 ),
            doubleArrayOf( 59.0, 95.6, 100.2, 104.8, 109.4, 114.0, 118.6, 123.2 ),
            doubleArrayOf( 60.0, 96.1, 100.7, 105.3, 110.0, 114.6, 119.2, 123.9 )
        )

        var batasSangatStunted = 0.0
        var batasStunted = 0.0
        var batasNormal = 0.0
        if ( gender!! == "P" ) {
            batasSangatStunted = dataIdealP[usia!!][1]
            batasStunted = dataIdealP[usia!!][2]
            batasNormal = dataIdealP[usia!!][7]
        }
        else {
            batasSangatStunted = dataIdealL[usia!!][1]
            batasStunted = dataIdealL[usia!!][2]
            batasNormal = dataIdealL[usia!!][7]
        }

        rentang = "Rentang tinggi ideal adalah $batasStunted - $batasNormal cm"
        if ( tb!!.toDouble() <= batasStunted ) {
            idealStatus = "Tinggi anak Anda masih di bawah tinggi ideal"
        }
        else if ( tb!!.toDouble() > batasStunted && tb!!.toDouble() <= batasNormal) {
            idealStatus = "Tinggi anak Anda sudah ideal"
            rentang = "Pertahankan dalam rentang $batasStunted - $batasNormal cm"
        }
        else if ( tb!!.toDouble() > batasNormal ) {
            idealStatus = "Anak Anda tinggi"
        }

        idealStatus?.let { resultIdeal.add(it) }
        rentang?. let { resultIdeal.add(it) }

        return resultIdeal
    }

    fun styleChartKiddo(lineChart: LineChart) = lineChart.apply {
        axisRight.isEnabled = true

        axisLeft.apply {
            isEnabled = false
            axisMinimum = 0f
        }

        xAxis.apply {
            axisMinimum = 0f
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(false)
            position = XAxis.XAxisPosition.BOTTOM
        }
        setTouchEnabled(true)
        isDragEnabled = true
        description = null

    }

    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(this@KiddoJournalActivity, R.color.prime_dark)
        valueTextColor = ContextCompat.getColor(this@KiddoJournalActivity, R.color.prime_secunder)
        lineWidth = 3f
        isHighlightEnabled = true
        mode = LineDataSet.Mode.CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(this@KiddoJournalActivity, R.drawable.bg_spark_line)

    }
}