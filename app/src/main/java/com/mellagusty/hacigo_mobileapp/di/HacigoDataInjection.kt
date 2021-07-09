package com.mellagusty.hacigo_mobileapp.di

import android.app.Application
import android.content.Context
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDatabase

object HacigoDataInjection {

    fun provideRepository(context: Context): Repository{

        val database = KiddoJournalDatabase.getDatabase(context)
        val kiddoJLocalDatasource = KiddoJLocalDatasource.getInstances(database.kiddoJournalDao())

        return Repository.getInstance(kiddoJLocalDatasource, context.applicationContext as Application)
    }
}