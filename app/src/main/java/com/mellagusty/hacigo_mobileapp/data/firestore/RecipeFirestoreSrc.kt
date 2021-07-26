package com.mellagusty.hacigo_mobileapp.data.firestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class RecipeFirestoreSrc {

    companion object {
        private var INSTANCE: RecipeFirestoreSrc? = null

        fun getInstances(): RecipeFirestoreSrc =
            INSTANCE ?: RecipeFirestoreSrc().apply {
                INSTANCE = this
            }
    }

    fun getRecipesData(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        FirebaseFirestore.getInstance().collection("cook").get().addOnSuccessListener { result ->
            val listData = mutableListOf<RecipesEntity>()
            for (document in result) {
                val judul = document.getString("judul")
                val subJudul = document.getString("subJudul")
                val imageUrl = document.getString("imageUrl")
                val bahan = document.get("bahan") as ArrayList<String>
                val caraBuat = document.get("caraBuat") as ArrayList<String>
                val recipes = RecipesEntity(
                    judul,
                    subJudul,
                    bahan,
                    caraBuat,
                    imageUrl
                )
                Log.d("TAG","Cek data resep : $recipes")
                listData.add(recipes)
            }
            mutableData.value = listData
        }
        Log.d("TAG","Data yang akan di return $mutableData")
        return mutableData
    }

}