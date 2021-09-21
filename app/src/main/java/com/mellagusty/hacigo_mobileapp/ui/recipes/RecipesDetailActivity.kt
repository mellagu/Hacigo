package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.databinding.ActivityRecipesDetailBinding

class RecipesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesDetailBinding
    private lateinit var recipesViewModel: RecipesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        recipesViewModel = ViewModelProvider(this, factory).get(RecipesViewModel::class.java)

        val judul = intent.getStringExtra(EXTRA_JUDUL)

        val recipeData = judul?.let { recipesViewModel.fetchARecipe(it) }

        judul?.let {
            recipesViewModel.fetchARecipe(it).observe(this, {
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(binding.ivRecipe)

                binding.tvTitleRecipe.text = it.judul
                binding.tvSubTitleRecipe.text = it.subJudul
                binding.tvBahan.text = it.bahan.toString()
                binding.tvCaraBuat.text = it.caraBuat.toString()



            })
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
        const val EXTRA_JUDUL = "judul"
        const val EXTRA_SUBJUDUL = "subjudul"
        const val EXTRA_BAHAN = "bahan"
        const val EXTRA_CARA = "cara"
        const val EXTRA_IMAGE = "image"
        const val EXTRA_NUTRISI = "nutrisi"
    }

}