package com.mellagusty.hacigo_mobileapp.data.local.journal

class KiddoJLocalDatasource(private val dao: KiddoJournalDao) {

    companion object {
        private var INSTANCE: KiddoJLocalDatasource? = null

        fun getInstances(dao: KiddoJournalDao): KiddoJLocalDatasource =
            INSTANCE ?: KiddoJLocalDatasource(dao).apply {
                INSTANCE = this
            }

    }

    suspend fun getAllJournal(): List<KiddoJournalEntity> {
        return dao.getAllJournal()
    }

    suspend fun getSpecificJournal(id: Int): KiddoJournalEntity {
        return dao.getSpecificJournal(id)
    }

    suspend fun insertJournal(journalEntity: KiddoJournalEntity) {
        return dao.insertJournal(journalEntity)
    }

    suspend fun updateJournal(journalEntity: KiddoJournalEntity) {
        return dao.updateJournal(journalEntity)
    }

    suspend fun deleteSpecificJournal(id: Int) {
        return dao.deleteSpecificJournal(id)
    }

    suspend fun getLastJournal(): KiddoJournalEntity {
        return dao.getLastJournal()
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