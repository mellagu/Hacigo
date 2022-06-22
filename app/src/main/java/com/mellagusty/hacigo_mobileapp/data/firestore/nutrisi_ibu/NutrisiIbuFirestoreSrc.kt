package com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.utils.Constant

class NutrisiIbuFirestoreSrc {

    companion object {
        private var INSTANCE: NutrisiIbuFirestoreSrc? = null

        fun getInstance(): NutrisiIbuFirestoreSrc =
            INSTANCE ?: NutrisiIbuFirestoreSrc().apply {
                INSTANCE = this
            }
    }


    private val nutrisiDb = FirebaseFirestore.getInstance().collection(Constant.NUTRISI_HAMIL)

    fun getNutritionData(): LiveData<MutableList<NutrisiIbuEntity>> {
        val mutableData = MutableLiveData<MutableList<NutrisiIbuEntity>>()
        nutrisiDb.get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<NutrisiIbuEntity>()
                for (document in result) {
                    val nutrisi = document.toObject(NutrisiIbuEntity::class.java)
                    Log.d("this", "Firestore ini data anda $nutrisi")
                    listData.add(nutrisi)
                }
                mutableData.postValue(listData)
            }
        Log.d("this is", "ini data Anda untuk return$mutableData")
        return mutableData
    }

    fun getANutrition(judul: String): LiveData<NutrisiIbuEntity> {
        val mutableData = MutableLiveData<NutrisiIbuEntity>()
        var nutrisi = NutrisiIbuEntity()
        nutrisiDb
            .whereEqualTo("judul", judul)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    Log.d("this", "Firestore detail nutrisi != null : ${it.documents.get(0).data?.get("imageUrl")}")
                    Log.d("this", "ln 61 cek judul $judul")

                    val nutrisiNew = NutrisiIbuEntity(
                        imageUrl = it.documents.get(0).data?.get("imageUrl") as String?,
                        judul = it.documents.get(0).data?.get("judul") as String?,
                        p0 = it.documents.get(0).data?.get("p0") as ArrayList<String>?,
                        p0title = it.documents.get(0).data?.get("p0title") as String?,
                        p1 = it.documents.get(0).data?.get("p1") as ArrayList<String>?,
                        p1title = it.documents.get(0).data?.get("p1title") as String?,

                    )
//                    nutrisi = it.toObject(NutrisiIbuEntity::class.java)!!
                    Log.d("this", "ln 64 " + nutrisi)
                    mutableData.postValue(nutrisiNew)

                } else {
                    Log.d("this", "Firestore detail ln 63, kosong")
                }
            }
        return mutableData
    }
}