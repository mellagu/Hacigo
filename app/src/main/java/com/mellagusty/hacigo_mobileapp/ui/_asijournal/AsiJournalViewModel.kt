package com.mellagusty.hacigo_mobileapp.ui._asijournal

import androidx.lifecycle.ViewModel
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJournalEntity

class AsiJournalViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getAllAsiJournal(): List<AsiJournalEntity> {
        return repository.getAllAsiJournal()
    }

    suspend fun getJournalByBulan(bulanke: String): String {
        return repository.getJournalByBulan(bulanke)
    }

    fun insertAsiJournal(asiJournalEntity: AsiJournalEntity) {
        return repository.insertAsiJournal(asiJournalEntity)
    }
}