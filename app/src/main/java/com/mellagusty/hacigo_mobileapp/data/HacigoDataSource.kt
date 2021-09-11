package com.mellagusty.hacigo_mobileapp.data

import androidx.lifecycle.LiveData
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipesEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity

interface HacigoDataSource {

    //For Remote One
    //Journal
    suspend fun getJournalAll(): List<KiddoJournalEntity>

    suspend fun getAllPregnantJournal(): List<PregnantJournalEntity>

    suspend fun getSpecificAllJournal(id: Int): KiddoJournalEntity

    suspend fun getSpecificAllPregnantJournal(id: Int): PregnantJournalEntity

    //Add Delete Update for Local Journal
    fun insertToJournal(journalEntity: KiddoJournalEntity)

    fun insertToPregnantJournal(journalEntity: PregnantJournalEntity)

    fun deleteSpecificJournal(id:Int)

    fun deleteSpecificPregnantJournal(id:Int)

    fun updateJournal(journalEntity: KiddoJournalEntity)

    //For Network One
    //Recipes
    fun getRecipesData(): LiveData<MutableList<RecipesEntity>>

    fun getARecipe(judul: String): LiveData<RecipesEntity>

    fun getRecipesByBahan(bahan : String): LiveData<MutableList<RecipesEntity>>


}