package com.mellagusty.hacigo_mobileapp.data.firestore.recipe

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
                    val recipes = document.toObject(RecipesEntity::class.java)
                    Log.d("this", "Firestore, ini data Anda $recipes")
                    listData.add(recipes)
                }
                mutableData.postValue(listData)
            }
        Log.d("this is", "ini data Anda untuk return$mutableData")
        return mutableData
    }

    fun getARecipe(judul: String): LiveData<RecipesEntity> {
        val mutableData = MutableLiveData<RecipesEntity>()
        var recipe = RecipesEntity()
        FirebaseFirestore.getInstance().collection("cook").document(judul)
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    Log.d("this", "Firestore detail ln 59, if result != null : $result")
                    Log.d("this", "ln 61 cek judul $judul")

                    recipe = result.toObject(RecipesEntity::class.java)!!
                    Log.d("this", "ln 64 " +  recipe)
                    mutableData.postValue(recipe)

                } else {
                    Log.d("this", "Firestore detail ln 63, kosong")
                }
            }
        return mutableData
    }

    fun getRecipesByBahan( bahan : String ): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        Log.d("firestore", "Firestore bahan : $bahan")
        FirebaseFirestore.getInstance().collection("cook")
//            .whereGreaterThanOrEqualTo("bahan", bahan)
            .whereArrayContains("bahanUtama", bahan)
            .get().addOnSuccessListener { result ->
            val listData = mutableListOf<RecipesEntity>()
            for (document in result) {
                val recipes = document.toObject(RecipesEntity::class.java)
                Log.d("this", "Firestore, ini data dengan bahan $bahan : $recipes")
                listData.add(recipes)
            }
            mutableData.postValue(listData)
        }
        Log.d("this is", "ini data Anda untuk return $mutableData")
        return mutableData
    }



}