package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity

class CreateJournalViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getSpecificAllJournal(noteId: Int): KiddoJournalEntity {
        return repository.getSpecificAllJournal(noteId)
    }

    suspend fun getSpecificAllPregnantJournal(noteId: Int): PregnantJournalEntity {
        return repository.getSpecificAllPregnantJournal(noteId)
    }

    fun insertToJournal(journalEntity: KiddoJournalEntity) {
        repository.insertToJournal(journalEntity)
    }

    fun insertToPregnantJournal(journalEntity: PregnantJournalEntity) {
        repository.insertToPregnantJournal(journalEntity)
    }

    fun updateJournal(journalEntity: KiddoJournalEntity) {
        repository.updateJournal(journalEntity)
    }

    fun deleteSpecificJournal(id: Int) {
        repository.deleteSpecificJournal(id)
    }

    fun deleteSpecificPregnantJournal(id: Int) {
        repository.deleteSpecificPregnantJournal(id)
    }

    suspend fun getLastJournal(): KiddoJournalEntity {
        return repository.getLastJournal()
    }




}