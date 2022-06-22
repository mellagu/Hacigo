package com.mellagusty.hacigo_mobileapp.ui._pregnantjournal

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
import com.mellagusty.hacigo_mobileapp.adapter.NutrisiHamilAdapter
import com.mellagusty.hacigo_mobileapp.adapter.PregnantJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityPregnantJournalBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.CreateJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu.NutrisiHamilActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class PregnantJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPregnantJournalBinding
    private lateinit var pregnantJournalAdapter: PregnantJournalAdapter
    private lateinit var viewModel: PregnantJournalViewModel
    private var noteId = -1

    private var list: List<PregnantJournalEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregnantJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PregnantJournalViewModel::class.java)


        pregnantJournalAdapter = PregnantJournalAdapter {
            val intent = Intent(this, CreatePregnantJournalActivity::class.java)
            intent.putExtra("note_id", it.id)
            startActivity(intent)
        }

        //back press
        binding.arrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this, CreatePregnantJournalActivity::class.java)
            intent.putExtra("note_id",-1)
            startActivity(intent)
        }

        binding.rvMomNutrition.setOnClickListener {
            val intent = Intent(this, NutrisiHamilActivity::class.java)
            startActivity(intent)
        }


        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerView.adapter = pregnantJournalAdapter


        searchBar()
        getPregnantInfo()
        getAllJournal()

        viewModel.lineDataSet.observe(this) { lineDataSet ->
            styleLineDataSet(lineDataSet)
            binding.lineChartPregnant.data = LineData(lineDataSet)
            binding.lineChartPregnant.invalidate()
        }
        styleChart(binding.lineChartPregnant)
        viewModel.getPregnantJournalData()

    }

    private fun searchBar() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tempArray = ArrayList<PregnantJournalEntity>()
                for (array in list ){
                    if (array.title!!.toLowerCase(Locale.getDefault()).contains(newText.toString())){
                        tempArray.add(array)
                    }
                }
                pregnantJournalAdapter.setListData(tempArray)
                pregnantJournalAdapter.notifyDataSetChanged()
                return true
            }
        })
    }


    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = viewModel.getPregnantJournalAll()

            pregnantJournalAdapter.setListData(journal)
            list = journal
            binding.recyclerView.adapter = pregnantJournalAdapter
        }
    }

    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(this@PregnantJournalActivity, R.color.prime_dark)
        valueTextColor = ContextCompat.getColor(this@PregnantJournalActivity, R.color.prime_secunder)
        lineWidth = 3f
        isHighlightEnabled = true
        mode = LineDataSet.Mode.CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(this@PregnantJournalActivity, R.drawable.bg_spark_line)

    }

    fun styleChart(lineChart: LineChart) = lineChart.apply {
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

    fun getPregnantInfo() {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PregnantJournalViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val pregnantInfo = viewModel.getLastPregnantJournal()

            if(pregnantInfo != null){
                val resultIdeal = dataBbIdeal(pregnantInfo)

                binding.tvStatusidealPregnant.setText(resultIdeal[0])
                binding.tvRentangidealPregnant.setText(resultIdeal[1])
            } else {
                Log.d("pregnant","Belum ada data yang dimasukkan")
            }



        }

    }

    fun dataBbIdeal(pregnantInfo: PregnantJournalEntity): ArrayList<String> {
        // seharusnya ini bb sebelum hamil
        val bbBefore = pregnantInfo.weight?.toDouble()
        val bbNow = pregnantInfo.weight?.toDouble()
        val tb = pregnantInfo.height?.toDouble()?.div(100)
        val bulan = pregnantInfo.ageInMonth?.toDouble()

        val imt = bbBefore?.div((tb?.times(tb)!!))


        var batasBawah: Double = 0.0
        var batasAtas: Double = 0.0

        var status: String = ""
        var idealStatus: String = "Tidak ada rekomendasi data"
        var rekomendasi: String = "Pastikan Anda mengonsumsi makanan bergizi seimbang"
        var resultIdeal = arrayListOf<String>()

        if ( bulan!! >=4 ) {
            if ( imt!! < 18.5 ) {
                batasBawah = bbBefore?.plus(bulan?.times(2))!!
                batasAtas = bbBefore?.plus(bulan?.times(3))!!
            }
            else if ( imt!! >= 18.5 && imt!! <= 24.9 ) {
                batasBawah = bbBefore?.plus(bulan?.times(1.9))!!
                batasAtas = bbBefore?.plus(bulan?.times(2.7))!!

            }
            else if ( imt!! >= 25 && imt!! <= 29.9 ) {
                batasBawah = bbBefore?.plus(bulan?.times(1.2))!!
                batasAtas = bbBefore?.plus(bulan?.times(1.9))!!
            }
            else {
                batasBawah = bbBefore?.plus(bulan?.times(0.8))!!
                batasAtas = bbBefore?.plus(bulan?.times(1.5))!!
            }

            rekomendasi = "Berat badan yang direkomendasikan adalah $batasBawah kg - $batasAtas kg"

            if ( bbNow!! < batasBawah ) {
                status = "underweight"
            }
            else if ( bbNow!! >= batasBawah && bbNow!! <= batasAtas ) {
                status = "ideal"
                rekomendasi = "Terus jaga berat badan Anda dan terus konsumsi makanan bergizi seimbang"
            }
            else {
                status = "overweight"
            }

            idealStatus = "Status berat badan Ibu adalah $status"
        }

        rekomendasi.let { resultIdeal.add(it) }
        idealStatus.let { resultIdeal.add(it) }

        return resultIdeal
    }
}