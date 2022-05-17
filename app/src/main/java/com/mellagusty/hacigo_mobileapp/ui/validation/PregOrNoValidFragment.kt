package com.mellagusty.hacigo_mobileapp.ui.validation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.FragmentPregOrNoBinding


class PregOrNoValidFragment : Fragment() {

    private lateinit var _binding: FragmentPregOrNoBinding
    private val binding get() = _binding
    var isPregOrNoFragmentLoaded = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPregOrNoBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val manager = activity?.supportFragmentManager?.beginTransaction()

        binding.cvMomPregnant.setOnClickListener {
            manager?.replace(R.id.validation_container, PregnantFieldFragment())
            manager?.addToBackStack(null)
            manager?.commit()
        }

        binding.cvMomKiddo.setOnClickListener {
            val supportFragment = activity?.supportFragmentManager
            supportFragment?.findFragmentById(R.id.container_register)
            val fragmentTransaction = supportFragment?.beginTransaction()
            fragmentTransaction?.replace(android.R.id.content, KiddoIdentityFragment())
            fragmentTransaction?.commit()

        }

        super.onViewCreated(view, savedInstanceState)

    }

}