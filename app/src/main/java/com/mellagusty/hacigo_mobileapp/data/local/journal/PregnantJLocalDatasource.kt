package com.mellagusty.hacigo_mobileapp.data.local.journal

class PregnantJLocalDatasource(private val dao: PregnantJournalDao) {

    companion object {
        private var INSTANCE: PregnantJLocalDatasource? = null

        fun getInstances(dao: PregnantJournalDao): PregnantJLocalDatasource =
            INSTANCE ?: PregnantJLocalDatasource(dao).apply {
                INSTANCE = this
            }

    }

    // untuk PregnantJLocalDatasource
    suspend fun getAllPregnantJournal(): List<PregnantJournalEntity> {
        return dao.getAllPregnantJournal()
    }

    suspend fun getSpecificPregnantJournal(id: Int): PregnantJournalEntity {
        return dao.getSpecificPregnantJournal(id)
    }

    suspend fun insertPregnantJournal(journalEntity: PregnantJournalEntity) {
        return dao.insertPregnantJournal(journalEntity)
    }

    suspend fun updatePregnantJournal(journalEntity: PregnantJournalEntity) {
        return dao.updatePregnantJournal(journalEntity)
    }

    suspend fun deleteSpecificPregnantJournal(id: Int) {
        return dao.deleteSpecificPregnantJournal(id)
    }

    suspend fun getLastPregnantJournal(): PregnantJournalEntity {
        return dao.getLastPregnantJournal()
    }

}