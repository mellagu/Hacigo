package com.mellagusty.hacigo_mobileapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.CreateJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalViewModel

class ViewModelFactory private constructor(private val repository: Repository): ViewModelProvider.Factory{

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?:
                ViewModelFactory(
                    HacigoDataInjection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateJournalViewModel::class.java)){
            return CreateJournalViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(KiddoJournalViewModel::class.java)){
            return KiddoJournalViewModel(repository) as T
        }
        throw IllegalArgumentException()
    }


}