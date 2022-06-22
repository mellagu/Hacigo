package com.mellagusty.hacigo_mobileapp.di

import android.app.Application
import android.content.Context
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu.NutrisiIbuFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipeFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJLocalDataSource
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalDatabase
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJLocalDatasource

object HacigoDataInjection {

    fun provideRepository(context: Context): Repository{

        val database = KiddoJournalDatabase.getDatabase(context)

        val kiddoJLocalDatasource = KiddoJLocalDatasource.getInstances(database.kiddoJournalDao())

        val pregnantJLocalDatasource = PregnantJLocalDatasource.getInstances(database.pregnantJournalDao())

        val asiJLocalDatasource = AsiJLocalDataSource.getInstances(database.asiJournalDao())

        val recipesFirestoreSrc = RecipeFirestoreSrc.getInstances()

        val imunisasiFirestoreSrc = ImunisasiFirestoreSrc.getInstance()

        val nutrisiIbuFirestoreSrc = NutrisiIbuFirestoreSrc.getInstance()

        return Repository.getInstance(kiddoJLocalDatasource, pregnantJLocalDatasource, asiJLocalDatasource, recipesFirestoreSrc, imunisasiFirestoreSrc, nutrisiIbuFirestoreSrc, context.applicationContext as Application)
    }
}