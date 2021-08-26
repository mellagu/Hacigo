package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity

class CreateJournalViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getSpecificAllJournal(noteId: Int): KiddoJournalEntity {
        return repository.getSpecificAllJournal(noteId)
    }

    fun insertToJournal(journalEntity: KiddoJournalEntity) {
        repository.insertToJournal(journalEntity)
    }

    fun updateJournal(journalEntity: KiddoJournalEntity) {
        repository.updateJournal(journalEntity)
    }

    fun deleteSpecificJournal(id: Int) {
        repository.deleteSpecificJournal(id)
    }




}