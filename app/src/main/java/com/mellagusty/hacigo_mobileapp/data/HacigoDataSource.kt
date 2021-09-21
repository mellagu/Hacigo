package com.mellagusty.hacigo_mobileapp.data

import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity

interface HacigoDataSource {

    //For Remote One
    //Journal
    suspend fun getJournalAll(): List<KiddoJournalEntity>

    suspend fun getSpecificAllJournal(id: Int): KiddoJournalEntity

    //Add Delete Update for Local Journal
    fun insertToJournal(journalEntity: KiddoJournalEntity)

    fun deleteSpecificJournal(id:Int)

    fun updateJournal(journalEntity: KiddoJournalEntity)

    //For Network One
    //Recipes
//    fun getRecipesData(): LiveData<MutableList<RecipesEntity>>

}