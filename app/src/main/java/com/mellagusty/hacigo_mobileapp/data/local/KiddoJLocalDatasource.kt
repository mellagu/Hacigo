package com.mellagusty.hacigo_mobileapp.data.local

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


}