package com.mellagusty.hacigo_mobileapp.ui._pregnantjournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity

class PregnantJournalViewModel(private val repository: Repository) : ViewModel() {

    suspend fun updateJournal(journalEntity: KiddoJournalEntity){
        return repository.updateJournal(journalEntity)
    }

    suspend fun getPregnantJournalAll(): List<PregnantJournalEntity> {
        return repository.getAllPregnantJournal()
    }
}