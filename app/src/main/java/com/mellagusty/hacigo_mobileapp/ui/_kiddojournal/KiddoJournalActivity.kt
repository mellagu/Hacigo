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

        var idx: Int? = 0
        var tbIdealMin: Double? = 0.0
        var tbIdealMax: Double? = 0.0
        var bbIdealMin: Double? = 0.0
        var bbIdealMax: Double? = 0.0

        val dataIdealP: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 2.3, 4.2, 45.6, 52.6 ),
            doubleArrayOf( 1.0, 3.2, 5.4, 50.0, 57.2 ),
            doubleArrayOf( 2.0, 4.0, 6.4, 53.2, 60.8 ),
            doubleArrayOf( 3.0, 4.5, 7.4, 55.8, 63.7 ),
            doubleArrayOf( 4.0, 5.1, 8.0, 57.9, 66.2 ),
            doubleArrayOf( 5.0, 5.4, 8.6, 59.9, 68.3 ),
            doubleArrayOf( 6.0, 5.6, 9.2, 61.3, 69.8 ),
            doubleArrayOf( 7.0, 6.1, 9.6, 62.8, 71.5 ),
            doubleArrayOf( 8.0, 6.2, 10.0, 64.3, 73.1 ),
            doubleArrayOf( 9.0, 6.5, 10.4, 65.5, 74.5 ),
            doubleArrayOf( 10.0, 6.7, 10.6, 66.7, 76.0 ),
            doubleArrayOf( 11.0, 7.0, 11.0, 68.0, 77.3 ),
            doubleArrayOf( 12.0, 7.1, 11.3, 69.2, 79.0 ),
            doubleArrayOf( 13.0, 7.0, 11.5, 68.9, 79.2 ),
            doubleArrayOf( 14.0, 9.0, 14.8, 80.0, 92.9 ),
            doubleArrayOf( 15.0, 10.0, 18.1, 87.4, 101.7 ),
            doubleArrayOf( 16.0, 12.3, 21.5, 94.1, 111.3 ),
            doubleArrayOf( 17.0, 13.7, 24.9, 99.9, 118.9 )
        )

        val dataIdealL: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 2.4, 4.2, 46.2, 53.3 ),
            doubleArrayOf( 1.0, 3.4, 5.6, 51.0, 58.4 ),
            doubleArrayOf( 2.0, 4.3, 7.0, 54.5, 62.1 ),
            doubleArrayOf( 3.0, 5.1, 7.8, 57.5, 65.3 ),
            doubleArrayOf( 4.0, 5.5, 8.5, 59.9, 67.9 ),
            doubleArrayOf( 5.0, 6.1, 9.2, 61.7, 69.7 ),
            doubleArrayOf( 6.0, 6.3, 9.6, 63.5, 71.5 ),
            doubleArrayOf( 7.0, 6.7, 10.2, 65.0, 73.1 ),
            doubleArrayOf( 8.0, 7.0, 10.4, 66.4, 74.6 ),
            doubleArrayOf( 9.0, 7.3, 10.8, 67.7, 76.0 ),
            doubleArrayOf( 10.0, 7.4, 11.2, 68.9, 77.5 ),
            doubleArrayOf( 11.0, 7.5, 11.4, 70.1, 78.8 ),
            doubleArrayOf( 12.0, 7.8, 11.8, 71.2, 80.0 ),
            doubleArrayOf( 13.0, 7.7, 12.0, 71.0, 80.5 ),
            doubleArrayOf( 14.0, 9.7, 15.3, 81.7, 93.9 ),
            doubleArrayOf( 15.0, 11.3, 18.3, 88.7, 103.5 ),
            doubleArrayOf( 16.0, 12.7, 21.2, 94.9, 111.7 ),
            doubleArrayOf( 17.0, 14.1, 24.2, 100.7, 119.2 )
        )

        if ( usia!! <= 12 ) {
            idx = usia
        }
        else if ( usia!! >= 13 && usia!! <= 24 ) { // usia >1 tahun
            idx = 13
        }
        else if ( usia!! >= 25 && usia!! <= 36 ) { // usia >2 tahun
            idx = 14
        }
        else if ( usia!! >= 37 && usia!! <= 48 ) { // usia >3 tahun
            idx = 15
        }
        else if ( usia!! >= 49 && usia!! <= 59 ){ // usia >4 tahun
            idx = 16
        }
        else { // usia >5 tahun
            idx = 17
        }

        if ( gender!! == "P" ) {
            bbIdealMin = dataIdealP[idx][1]
            bbIdealMax = dataIdealP[idx][2]
            tbIdealMin = dataIdealP[idx][3]
            tbIdealMax = dataIdealP[idx][4]
        }
        else {
            bbIdealMin = dataIdealL[idx][1]
            bbIdealMax = dataIdealL[idx][2]
            tbIdealMin = dataIdealL[idx][3]
            tbIdealMax = dataIdealL[idx][4]
        }

        rentang = "Rentang yang ideal adalah $bbIdealMin - $bbIdealMax kg"
        if ( tb!!.toDouble() >= tbIdealMin && tb!!.toDouble() <= tbIdealMax ) {
            if ( bb!!.toDouble() >= bbIdealMin && bb!!.toDouble() <= bbIdealMax ) {
                idealStatus = "Berat badan anak sudah ideal"
                rentang = "Pertahankan dalam rentang $bbIdealMin - $bbIdealMax kg"
            }
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