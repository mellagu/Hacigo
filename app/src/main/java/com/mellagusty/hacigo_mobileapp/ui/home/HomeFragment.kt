package com.mellagusty.hacigo_mobileapp.ui.home

import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
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
import com.mellagusty.hacigo_mobileapp.ui.MainJournalActivity
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


        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        Log.d("TAG", "onViewCreated: factory")

        binding.cardJournal.setOnClickListener {
            val intent = Intent(requireContext(), MainJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvPickFood.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.cvParenthood.setOnClickListener {
            findNavController().navigate(R.id.action_to_parenthood)
        }

        binding.cvKia.setOnClickListener {

            val webIntent: Intent = Uri.parse("https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiPusurxP7yAhVz73MBHYUHCiQQFnoECAIQAQ&url=https%3A%2F%2Fkesga.kemkes.go.id%2Fassets%2Ffile%2Fpedoman%2FBUKU%2520KIA%2520REVISI%25202020%2520LENGKAP.pdf&usg=AOvVaw2pp9jF7BPBkyIZq9fWqfBK").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)

        }

        showLoading(false)
        Log.d("TAG", "onViewCreated: homeViewModel.lineDataSet.observe(requireActivity()) AWAL")

        Log.d("TAG", "onViewCreated: homeViewModel.lineDataSet.observe(requireActivity()) AKHIR")
        homeViewModel.getJournalData()
        Log.d("TAG", "onViewCreated: homeViewModel.getJournalData()")



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