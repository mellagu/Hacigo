package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipesEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityRecipesDetailBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class RecipesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra(EXTRA_JUDUL)
        val subjudul = intent.getStringExtra(EXTRA_SUBJUDUL)
        val nutrisi = intent.getStringExtra(EXTRA_NUTRISI)
        val bahan = intent.getStringArrayListExtra(EXTRA_BAHAN)
        val cara = intent.getStringArrayListExtra(EXTRA_CARA)
        val image = intent.getIntExtra(EXTRA_IMAGE, -1)

        val bahanStr = convertToStringLines(bahan)
        val caraStr = convertToStringLines(cara)

        Glide.with(this)
            .load(image)
            .into(binding.ivRecipe)
        binding.tvTitleRecipe.text = judul.toString()
        binding.tvSubTitleRecipe.text = subjudul.toString()
        binding.descNutrition.text = nutrisi.toString()
        binding.tvBahan.text = bahanStr
        binding.tvCaraBuat.text = caraStr

        Log.d("cekini", bahan.toString())
        Log.d("cekini", cara.toString())
        Log.d("cek", bahanStr)
        Log.d("cek", caraStr)

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
        const val EXTRA_JUDUL = "judul"
        const val EXTRA_SUBJUDUL = "subjudul"
        const val EXTRA_BAHAN = "bahan"
        const val EXTRA_CARA = "cara"
        const val EXTRA_IMAGE = "image"
        const val EXTRA_NUTRISI = "nutrisi"
    }

}