package com.mellagusty.hacigo_mobileapp.data.firestore.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.internal.Mutable
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.utils.Constant

class RecipeFirestoreSrc {

    companion object {
        private var INSTANCE: RecipeFirestoreSrc? = null

        fun getInstances(): RecipeFirestoreSrc =
            INSTANCE ?: RecipeFirestoreSrc().apply {
                INSTANCE = this
            }
    }

    private val cookDb = FirebaseFirestore.getInstance().collection(Constant.COOK)

    fun getRecipesData(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        cookDb.get()
            .addOnSuccessListener { result ->
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

    fun getRecipeSixToTen(): LiveData<MutableList<RecipesEntity>> {
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        cookDb
            .whereGreaterThanOrEqualTo(Constant.USIA,6)
            .whereLessThanOrEqualTo(Constant.USIA,10)
            .get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<RecipesEntity>()
                for (document in result) {
                    val recipes = document.toObject(RecipesEntity::class.java)
                    listData.add(recipes)
                }
                mutableData.postValue(listData)
            }
        return mutableData
    }

    fun getRecipeElevenToEighteen(): LiveData<MutableList<RecipesEntity>>{
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        cookDb
            .whereGreaterThanOrEqualTo(Constant.USIA,11)
            .whereLessThanOrEqualTo(Constant.USIA,18)
            .get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<RecipesEntity>()
                for (document in result) {
                    val recipes = document.toObject(RecipesEntity::class.java)
                    listData.add(recipes)
                }
                mutableData.postValue(listData)
            }
        return mutableData
    }

    fun getRecipeNineteentoTwenfour(): LiveData<MutableList<RecipesEntity>>{
        val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
        cookDb
            .whereGreaterThanOrEqualTo(Constant.USIA,19)
            .whereLessThanOrEqualTo(Constant.USIA,25)
            .get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<RecipesEntity>()
                for (document in result) {
                    val recipes = document.toObject(RecipesEntity::class.java)
                    listData.add(recipes)
                }
                mutableData.postValue(listData)
            }
        return mutableData
    }


    fun getARecipe(judul: String): LiveData<RecipesEntity> {
        val mutableData = MutableLiveData<RecipesEntity>()
        var recipe = RecipesEntity()
        cookDb.document(judul)
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    Log.d("this", "Firestore detail resep != null : $result")
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
        cookDb.whereArrayContains("bahanUtama", bahan)
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