package com.mellagusty.hacigo_mobileapp.data.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDao

class RecipeFirestoreSrc {

    companion object{
        private var INSTANCE: RecipeFirestoreSrc? = null

        fun getInstances(): RecipeFirestoreSrc =
            INSTANCE ?: RecipeFirestoreSrc().apply {
                INSTANCE = this
            }
    }

    fun getRecipesData(): LiveData<MutableList<RecipesEntity>>{
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        FirebaseFirestore.getInstance().collection("cook").get().addOnSuccessListener { result ->
            val listData = mutableListOf<RecipesEntity>()
            for (document in result ){
                val judul = document.getString("judul")
                val subJudul = document.getString("subJudul")
                val bahan = document.getString("bahan")
                val caraBuat = document.getString("caraBuat")
                val imageUrl = document.getString("imageUrl")
            }
            mutableData.value = listData
        }
        return mutableData
    }
}