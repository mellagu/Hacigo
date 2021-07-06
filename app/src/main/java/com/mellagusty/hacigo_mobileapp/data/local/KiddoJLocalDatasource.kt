package com.mellagusty.hacigo_mobileapp.data.local

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KiddoJLocalDatasource(private val dao: KiddoJournalDao){

//    fun getAllJournal(): List<KiddoJournalEntity>{
//        CoroutineScope(Dispatchers.IO).launch{
//            coroutineContext.let {
//                var journal = dao.getAllJournal()
//            }
//        }
//    }
}