package com.mellagusty.hacigo_mobileapp.ui.validation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityValidationBinding

class ValidationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityValidationBinding
    var isPregOrNoFragmentLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPregOrNoFrag()
//        showPregnantFieldFrag()
//        showKiddoFieldFrag()
//        showKiddoValidFrag()
//        showPregnantFieldFrag()
    }


    private fun showPregOrNoFrag() {
        val transaction = manager.beginTransaction()
        val fragment = PregOrNoFragment()
        transaction.replace(R.id.validation_container,fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
//        isPregOrNoFragmentLoaded = true
    }
//    private fun showKiddoValidFrag() {
//        val transaction = manager.beginTransaction()
//        val fragment = PregOrNoFragment()
//        transaction.replace(R.id.validation_container,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//        isPregOrNoFragmentLoaded = true
//    }

//    private fun showKiddoFieldFrag() {
//        val transaction = manager.beginTransaction()
//        val fragment = PregOrNoFragment()
//        transaction.replace(R.id.validation_container,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//        isPregOrNoFragmentLoaded = true
//    }

//    private fun showPregnantFieldFrag() {
//        val transaction = manager.beginTransaction()
//        val fragment = PregnantFieldFragment()
//        transaction.replace(R.id.validation_container,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//        isPregOrNoFragmentLoaded = true
//
//    }
}