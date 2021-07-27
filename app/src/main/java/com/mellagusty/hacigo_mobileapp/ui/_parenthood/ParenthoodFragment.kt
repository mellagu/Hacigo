package com.mellagusty.hacigo_mobileapp.ui._parenthood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.FragmentParenthoodBinding


class ParenthoodFragment : Fragment() {

    private lateinit var binding : FragmentParenthoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentParenthoodBinding.inflate(inflater,container,false)
        return binding.root
    }



    companion object {
    }
}