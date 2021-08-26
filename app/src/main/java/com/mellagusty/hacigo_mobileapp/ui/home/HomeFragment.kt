package com.mellagusty.hacigo_mobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.tflite.imageclassification.sample.camera.CameraActivity
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityMainBinding
import com.mellagusty.hacigo_mobileapp.databinding.FragmentHomeBinding
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.CreateJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var journalViewModel: CreateJournalViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        Log.d("TAG", "onCreateView: ()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: MULAI!!!!!")

        getKiddoInfo()

        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        Log.d("TAG", "onViewCreated: factory")

        binding.cardJournal.setOnClickListener {
            val intent = Intent(requireContext(), KiddoJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvPickFood.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.cvParenthood.setOnClickListener {
            findNavController().navigate(R.id.action_to_parenthood)
        }

        showLoading(false)
        Log.d("TAG", "onViewCreated: homeViewModel.lineDataSet.observe(requireActivity()) AWAL")
        homeViewModel.lineDataSet.observe(requireActivity()) { lineDataSet ->
            styleLineDataSet(lineDataSet)
            binding.lineChart.data = LineData(lineDataSet)
            binding.lineChart.invalidate()
        }
        styleChart(binding.lineChart)
        Log.d("TAG", "onViewCreated: homeViewModel.lineDataSet.observe(requireActivity()) AKHIR")
        homeViewModel.getJournalData()
        Log.d("TAG", "onViewCreated: homeViewModel.getJournalData()")



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

    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(requireContext(),R.color.prime_dark)
        valueTextColor = ContextCompat.getColor(requireContext(),R.color.prime_secunder)
        lineWidth = 3f
        isHighlightEnabled = true
        mode = LineDataSet.Mode.CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(requireContext(),R.drawable.bg_spark_line)
        
    }

    //Show the progress bar while load
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getKiddoInfo() {
        val factory = ViewModelFactory.getInstance(requireContext())
        journalViewModel = ViewModelProvider(this, factory).get(CreateJournalViewModel::class.java)


//      yang akan ditampilkan : bb terakhir, tb terakhir, keterangan sudah ideal atau belum, rekomendasi berat ideal

        CoroutineScope(Dispatchers.IO).launch {
            val kiddoInfo = journalViewModel.getLastJournal()
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
}