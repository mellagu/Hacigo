package com.mellagusty.hacigo_mobileapp.ui._imunisasijournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiEntity

class ImunisasiJournalViewModel(private val repository: Repository) : ViewModel() {

    fun fetchImunisasiData(): LiveData<MutableList<ImunisasiEntity>>{
        val mutableData = MutableLiveData<MutableList<ImunisasiEntity>>()
        repository.getImunisasiData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }


}