package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity

class KiddoJournalViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getJournalAll(): List<KiddoJournalEntity> {
        return repository.getJournalAll()
    }

    suspend fun updateJournal(journalEntity: KiddoJournalEntity){
        return repository.updateJournal(journalEntity)
    }

    suspend fun getPregnantJournalAll(): List<PregnantJournalEntity> {
        return repository.getAllPregnantJournal()
    }
}