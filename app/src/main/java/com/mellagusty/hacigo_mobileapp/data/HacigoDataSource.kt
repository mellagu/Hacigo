package com.mellagusty.hacigo_mobileapp.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity

interface HacigoDataSource {

    //For Remote One


    //Journal
    fun getJournalAll(): LiveData<PagedList<KiddoJournalEntity>>

    fun getSpecificAllJournal(id: Int): KiddoJournalEntity

    //Add Delete Update for Local Journal
    fun insertToJournal(journalEntity: KiddoJournalEntity)

    fun removeJournal(id:Int)

    fun updateJournal(journalEntity: KiddoJournalEntity)
}