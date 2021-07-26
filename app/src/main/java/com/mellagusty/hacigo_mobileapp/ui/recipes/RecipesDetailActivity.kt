package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityRecipesDetailBinding

class RecipesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_detail)
    }

    companion object{
        const val EXTRA_RECIPES = "EXTRA_RECIPES"
    }
}