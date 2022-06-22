package com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu.NutrisiIbuEntity

class NutrisiIbuViewModel(private val repository: Repository): ViewModel() {

    fun fetchNutritionData(): LiveData<MutableList<NutrisiIbuEntity>> {
        val mutableData = MutableLiveData<MutableList<NutrisiIbuEntity>>()
        repository.getNutritionData().observeForever{ nutrisiList ->
            mutableData.value = nutrisiList
        }
        return mutableData
    }

    fun fetchANutrition(judul: String): LiveData<NutrisiIbuEntity> {
        val mutableData = MutableLiveData<NutrisiIbuEntity>()
        repository.getANutrition(judul).observeForever{ nutrisiData ->
            mutableData.value = nutrisiData
        }
        return mutableData
    }
}