package com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityNutrisiHamilDetailBinding
import com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu.NutrisiHamilActivity.Companion.EXTRA_JUDUL
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class NutrisiHamilDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNutrisiHamilDetailBinding
    private lateinit var nutrisiViewModel : NutrisiIbuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutrisiHamilDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        nutrisiViewModel = ViewModelProvider(this, factory).get(NutrisiIbuViewModel::class.java)

        val judul = intent.getStringExtra(EXTRA_JUDUL)

        judul?.let {
            nutrisiViewModel.fetchANutrition(it).observe(this){
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(binding.ivNutrisi)

                binding.tvP0Title.text = it.p0title
                binding.tvP0Desc.text = convertToStringLines(it.p0)
                binding.tvP1Title.text = it.p1title
                binding.tvP1Desc.text = convertToStringLines(it.p1)
            }
        }

        binding.arrowBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun convertToStringLines(arrstr: ArrayList<String>?): String {
        var newStr = ""
        arrstr?.forEach{
            newStr = newStr + it
            newStr = newStr + "\n"
        }
        Log.d("cek", newStr)
        return newStr

    }

    companion object{
        const val EXTRA_JUDUL = "EXTRA_JUDUL"
    }
}