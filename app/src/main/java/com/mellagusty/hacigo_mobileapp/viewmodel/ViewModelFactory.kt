package com.mellagusty.hacigo_mobileapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection
import com.mellagusty.hacigo_mobileapp.ui._asijournal.AsiJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._imunisasijournal.ImunisasiJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.CreateJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.CreatePregnantJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.PregnantJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui.home.HomeViewModel
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesViewModel

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
        if (modelClass.isAssignableFrom(CreatePregnantJournalViewModel::class.java)){
            return CreatePregnantJournalViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(KiddoJournalViewModel::class.java)){
            return KiddoJournalViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PregnantJournalViewModel::class.java)){
            return PregnantJournalViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AsiJournalViewModel::class.java)){
            return AsiJournalViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(ImunisasiJournalViewModel::class.java)){
            return ImunisasiJournalViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)){
            return RecipesViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException()
    }


}