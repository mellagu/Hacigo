package com.mellagusty.hacigo_mobileapp.ui.validation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.databinding.FragmentPregOrNoBinding
import com.mellagusty.hacigo_mobileapp.ui.MainJournalActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant


class PregOrNoValidFragment : Fragment() {

    private lateinit var _binding: FragmentPregOrNoBinding
    private val binding get() = _binding
    var isPregOrNoFragmentLoaded = true
    lateinit var userDetail: UserEmail


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPregOrNoBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = this.arguments
        val location = bundle?.getString(Constant.LOCATION," ")
        binding.tvLocation.text = "$location"

        userDetail = UserEmail()
        
        binding.cvMomPregnant.setOnClickListener {
            val intent = Intent(requireContext(), MainJournalActivity::class.java)
            intent.putExtra(Constant.PRIORITY,Constant.YES_PREGNANT)
            startActivity(intent)
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