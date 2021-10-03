package com.mellagusty.hacigo_mobileapp.ui.validation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.FragmentPregOrNoBinding


class PregOrNoFragment : Fragment() {

    private lateinit var _binding: FragmentPregOrNoBinding
    private val binding get() = _binding
    val manager = activity?.supportFragmentManager
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

        binding.cvMomPregnant.setOnClickListener {
            Toast.makeText(requireContext(), "haloww", Toast.LENGTH_SHORT).show()
            val transaction = manager?.beginTransaction()
            val fragment = PregnantFieldFragment()
            transaction?.replace(R.id.validation_container, fragment)
//            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        binding.cvMomKiddo.setOnClickListener {
            val transaction = manager?.beginTransaction()
            val fragment = KiddoIdentityFragment()
            transaction?.replace(R.id.validation_container, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        super.onViewCreated(view, savedInstanceState)

    }

}