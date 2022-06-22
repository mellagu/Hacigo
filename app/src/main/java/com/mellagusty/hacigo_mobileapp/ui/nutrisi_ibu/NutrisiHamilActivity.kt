package com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.NutrisiHamilAdapter
import com.mellagusty.hacigo_mobileapp.databinding.ActivityNutrisiHamilBinding
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesViewModel
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class NutrisiHamilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNutrisiHamilBinding
    private lateinit var adapter: NutrisiHamilAdapter
    private lateinit var viewModel: NutrisiIbuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityNutrisiHamilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(NutrisiIbuViewModel::class.java)

        viewModel.fetchNutritionData()

    }
    companion object{
        const val EXTRA_JUDUL = "EXTRA_JUDUL"
    }
}