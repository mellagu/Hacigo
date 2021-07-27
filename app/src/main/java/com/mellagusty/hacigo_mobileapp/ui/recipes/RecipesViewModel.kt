package com.mellagusty.hacigo_mobileapp.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipesEntity

class RecipesViewModel(private val repository: Repository) : ViewModel() {

    fun fetchUserData(): LiveData<MutableList<RecipesEntity>>{
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        repository.getRecipesData().observeForever{ recipeList->
            mutableData.value = recipeList
        }
        return mutableData
    }




}