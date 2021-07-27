package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipesEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityRecipesDetailBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class RecipesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesDetailBinding
    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[RecipesViewModel::class.java]

        //intent arrow back
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        observeViewModel()

    }

    private fun observeViewModel() {

    }

    companion object{
        const val EXTRA_RECIPES = "EXTRA_RECIPES"
    }
}