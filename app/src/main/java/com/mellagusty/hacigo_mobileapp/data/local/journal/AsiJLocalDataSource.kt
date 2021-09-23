package com.mellagusty.hacigo_mobileapp.data.local.journal

class AsiJLocalDataSource(private val dao: AsiJournalDao) {

    companion object {
        private var INSTANCE: AsiJLocalDataSource? = null

        fun getInstances(dao: AsiJournalDao): AsiJLocalDataSource =
            INSTANCE ?: AsiJLocalDataSource(dao).apply {
                INSTANCE = this
            }

    }

    suspend fun getAllAsiJournal(): List<AsiJournalEntity> {
        return dao.getAllAsiJournal()
    }

    fun insertAsiJournal(asiJournalEntity: AsiJournalEntity) {
        return dao.insertAsiJournal(asiJournalEntity)
    }

    suspend fun getJournalByBulan(bulanke: String): String {
        return dao.getJournalByBulan(bulanke)
    }
}