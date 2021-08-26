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
import com.mellagusty.hacigo_mobileapp.databinding.FragmentHomeBinding
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
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
}