package com.mellagusty.hacigo_mobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.tflite.imageclassification.sample.camera.CameraActivity
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.FragmentHomeBinding
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalActivity
import com.mellagusty.hacigo_mobileapp.ui._parenthood.ParenthoodFragment

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
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.cardJournal.setOnClickListener {
            val intent = Intent(requireContext(), KiddoJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvPickFood.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.cvParenthood.setOnClickListener {
//            val parenthood = ParenthoodFragment()
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.navigation_home,parenthood)
//            transaction.commit()
            findNavController().navigate(R.id.action_to_parenthood)
        }

        showLoading(false)

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