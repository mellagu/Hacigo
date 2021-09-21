package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository

class RecipesViewModel(private val repository: Repository) : ViewModel() {

    fun fetchRecipesData(): LiveData<MutableList<RecipesEntity>>{
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipesData().observeForever{ recipeList->
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