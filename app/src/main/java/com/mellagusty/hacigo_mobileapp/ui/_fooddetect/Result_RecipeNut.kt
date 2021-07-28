package com.mellagusty.hacigo_mobileapp.ui._fooddetect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesFragment
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesViewModel

class Result_RecipeNut : AppCompatActivity() {

    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_recipenut)
        val bahan = intent.getStringExtra("bahan")

        val frag = RecipesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.recycler_view,frag).commit()
        val bundle = Bundle()

        Log.d("Notif","Lagi di Result_RecipeNut")
        bundle.putString("bahan", bahan)
        frag.arguments = bundle


//        viewModel.fetchUserData()

    }
}