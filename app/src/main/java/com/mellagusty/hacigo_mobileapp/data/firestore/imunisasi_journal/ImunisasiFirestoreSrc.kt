package com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mellagusty.hacigo_mobileapp.utils.Constant

class ImunisasiFirestoreSrc {

    companion object {
        private var INSTANCE: ImunisasiFirestoreSrc? = null

        fun getInstance(): ImunisasiFirestoreSrc =
            INSTANCE ?: ImunisasiFirestoreSrc().apply {
                INSTANCE = this
            }
    }

    fun getImunisasiData(): LiveData<MutableList<ImunisasiEntity>> {
        val imunisasi = ImunisasiEntity()
        val mutableData = MutableLiveData<MutableList<ImunisasiEntity>>()
        FirebaseFirestore.getInstance().collection(Constant.IMUNISASI)
            .orderBy("bulan",Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
//                val filter = result.
            val listData = mutableListOf<ImunisasiEntity>()
            for (document in result) {
                val imunisasi = document.toObject(ImunisasiEntity::class.java)
                Log.d("tag","Ini data imunisasi anda $imunisasi")
                listData.add(imunisasi)
            }
            mutableData.postValue(listData)
        }
        Log.d("this is", "ini data Anda untuk return$mutableData")
    return mutableData
    }

    fun saveImunisasiStatus(){

        FirebaseFirestore.getInstance().collection(Constant.IMUNISASI)
            .document()
    }

}