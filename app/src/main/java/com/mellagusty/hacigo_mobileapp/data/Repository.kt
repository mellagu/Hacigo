package com.mellagusty.hacigo_mobileapp.data

import android.app.Application
import android.view.View
import com.mellagusty.hacigo_mobileapp.adapter.KiddoJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDao
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalDatabase
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val kiddoJLocalDatasource: KiddoJLocalDatasource,
    application: Application
): HacigoDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            localDataSource: KiddoJLocalDatasource,
            application: Application
        ): Repository =
            instance ?: synchronized(this) {
                Repository(localDataSource, application).apply { instance = this }
            }
    }

//    private var kiddoJournalDao: KiddoJournalDao?
//    private var kiddoJournalDatabase : KiddoJournalDatabase?
//    init {
//        kiddoJournalDatabase = KiddoJournalDatabase.getDatabase(application)
//        kiddoJournalDao = kiddoJournalDatabase?.kiddoJournalDao()
//    }

    override suspend fun getJournalAll(): List<KiddoJournalEntity> {

            return kiddoJLocalDatasource.getAllJournal()

//            arrNotes = notes as ArrayList<Notes>
//            recycler_view.adapter = notesAdapter

    }

    override fun getSpecificAllJournal(id: Int): KiddoJournalEntity {
        TODO("Not yet implemented")
    }

    override fun insertToJournal(journalEntity: KiddoJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            kiddoJLocalDatasource.insertJournal(journalEntity)
        }
    }

    override fun removeJournal(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateJournal(journalEntity: KiddoJournalEntity) {
        TODO("Not yet implemented")
    }
}