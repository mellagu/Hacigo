package com.mellagusty.hacigo_mobileapp.ui._fooddetect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesViewModel

class Result_RecipeNut : AppCompatActivity() {

    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_recipenut)

        viewModel.fetchUserData()
    }
}