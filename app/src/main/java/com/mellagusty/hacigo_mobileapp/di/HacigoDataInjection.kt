package com.mellagusty.hacigo_mobileapp.di

import android.app.Application
import android.content.Context
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipeFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalDatabase
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJLocalDatasource

object HacigoDataInjection {

    fun provideRepository(context: Context): Repository{

        val database = KiddoJournalDatabase.getDatabase(context)

        val kiddoJLocalDatasource = KiddoJLocalDatasource.getInstances(database.kiddoJournalDao())

        val pregnantJLocalDatasource = PregnantJLocalDatasource.getInstances(database.pregnantJournalDao())

        val recipesFirestoreSrc = RecipeFirestoreSrc.getInstances()

        return Repository.getInstance(kiddoJLocalDatasource, pregnantJLocalDatasource, recipesFirestoreSrc, context.applicationContext as Application)
    }
}