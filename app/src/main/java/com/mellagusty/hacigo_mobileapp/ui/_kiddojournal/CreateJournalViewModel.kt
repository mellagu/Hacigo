package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity

class CreateJournalViewModel(private val repository: Repository): ViewModel() {

    suspend fun insertToJournal(journalEntity: KiddoJournalEntity) {
        repository.insertToJournal(journalEntity)
    }


}