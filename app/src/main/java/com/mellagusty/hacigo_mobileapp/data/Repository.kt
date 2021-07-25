package com.mellagusty.hacigo_mobileapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipeFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.firestore.RecipesEntity
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJLocalDatasource
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val kiddoJLocalDatasource: KiddoJLocalDatasource,
    private val RecipeFirestoreSrc: RecipeFirestoreSrc,
    application: Application
) : HacigoDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            localDataSource: KiddoJLocalDatasource,
            RecipeFirestoreSrc: RecipeFirestoreSrc,
            application: Application
        ): Repository =
            instance ?: synchronized(this) {
                Repository(localDataSource, RecipeFirestoreSrc, application).apply {
                    instance = this
                }
            }
    }


    override suspend fun getJournalAll(): List<KiddoJournalEntity> {
        return kiddoJLocalDatasource.getAllJournal()
    }

    override suspend fun getSpecificAllJournal(id: Int): KiddoJournalEntity {
        return kiddoJLocalDatasource.getSpecificJournal(id)
    }

    override fun insertToJournal(journalEntity: KiddoJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            kiddoJLocalDatasource.insertJournal(journalEntity)
        }
    }

    override fun updateJournal(journalEntity: KiddoJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            kiddoJLocalDatasource.updateJournal(journalEntity)
        }
    }

    override fun deleteSpecificJournal(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            kiddoJLocalDatasource.deleteSpecificJournal(id)
        }
    }

    //firestore for recipes
    override fun getRecipesData(): LiveData<MutableList<RecipesEntity>> {
        return RecipeFirestoreSrc.getRecipesData()
    }

}