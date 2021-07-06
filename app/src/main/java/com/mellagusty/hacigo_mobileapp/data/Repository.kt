package com.mellagusty.hacigo_mobileapp.data

import android.app.Application
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDao
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDatabase
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity

class Repository(
    private val kiddoJLocalDatasource: KiddoJLocalDatasource,
    application: Application
): HacigoDataSource {

    private var kiddoJournalDao: KiddoJournalDao?
    private var kiddoJournalDatabase : KiddoJournalDatabase?
    init {
        kiddoJournalDatabase = KiddoJournalDatabase.getDatabase(application)
        kiddoJournalDao = kiddoJournalDatabase?.kiddoJournalDao()
    }

    override fun getJournalAll(): List<KiddoJournalEntity> {
        TODO("Not yet implemented")
    }

    override fun getSpecificAllJournal(id: Int): KiddoJournalEntity {
        TODO("Not yet implemented")
    }

    override fun insertToJournal(journalEntity: KiddoJournalEntity) {
        TODO("Not yet implemented")
    }

    override fun removeJournal(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateJournal(journalEntity: KiddoJournalEntity) {
        TODO("Not yet implemented")
    }
}