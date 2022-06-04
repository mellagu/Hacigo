package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity

class RecipesViewModel(private val repository: Repository) : ViewModel() {

    fun fetchRecipesData(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipesData().observeForever{ recipeList->
            mutableData.value = recipeList
        }
        return mutableData
    }

    fun fetchRecipeSixToTen(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipeSixToTen().observeForever{ recipeList->
            mutableData.value = recipeList
        }
        return mutableData
    }

    fun fetchRecipeElevenToEighteen(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipeElevenToEighteen().observeForever{ recipeList->
            mutableData.value = recipeList
        }
        return mutableData
    }

    fun fetchRecipeNineteentoTwenfour(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipeNineteentoTwenfour().observeForever{ recipeList->
            mutableData.value = recipeList
        }
        return mutableData
    }


    fun fetchARecipe(judul: String): LiveData<RecipesEntity> {
        val mutableData = MutableLiveData<RecipesEntity>()
        repository.getARecipe(judul).observeForever{ recipeData->
            mutableData.value = recipeData
        }
        return mutableData
    }

    fun fetchRecipesByBahan(bahan: String): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipesByBahan(bahan).observeForever{ recipeList ->
            mutableData.value = recipeList
        }
        return mutableData
    }



}