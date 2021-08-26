package com.mellagusty.hacigo_mobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.LineData
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)

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

        homeViewModel.lineDataSet.observe(requireActivity()) { lineDataSet ->
            binding.lineChart.data = LineData(lineDataSet)
            binding.lineChart.invalidate()
        }
        homeViewModel.getJournalData()

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