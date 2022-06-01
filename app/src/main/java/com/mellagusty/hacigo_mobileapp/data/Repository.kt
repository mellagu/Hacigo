package com.mellagusty.hacigo_mobileapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiEntity
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipeFirestoreSrc
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val kiddoJLocalDatasource: KiddoJLocalDatasource,
    private val pregnantJLocalDatasource: PregnantJLocalDatasource,
    private val asiJLocalDataSource: AsiJLocalDataSource,
    private val RecipeFirestoreSrc: RecipeFirestoreSrc,
    private val ImunisasiFirestoreSrc: ImunisasiFirestoreSrc,
    application: Application
) : HacigoDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            localDataSource: KiddoJLocalDatasource,
            pregnantJLocalDatasource: PregnantJLocalDatasource,
            asiJLocalDataSource: AsiJLocalDataSource,
            RecipeFirestoreSrc: RecipeFirestoreSrc,
            ImunisasiFirestoreSrc: ImunisasiFirestoreSrc,
            application: Application
        ): Repository =
            instance ?: synchronized(this) {
                Repository(localDataSource, pregnantJLocalDatasource, asiJLocalDataSource, RecipeFirestoreSrc, ImunisasiFirestoreSrc, application).apply {
                    instance = this
                }
            }
    }


    override suspend fun getJournalAll(): List<KiddoJournalEntity> {
        return kiddoJLocalDatasource.getAllJournal()
    }

    override suspend fun getAllPregnantJournal(): List<PregnantJournalEntity> {
        return pregnantJLocalDatasource.getAllPregnantJournal()
    }

    override suspend fun getSpecificAllJournal(id: Int): KiddoJournalEntity {
        return kiddoJLocalDatasource.getSpecificJournal(id)
    }

    override suspend fun getSpecificAllPregnantJournal(id: Int): PregnantJournalEntity {
        return pregnantJLocalDatasource.getSpecificPregnantJournal(id)
    }

    override fun insertToJournal(journalEntity: KiddoJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            kiddoJLocalDatasource.insertJournal(journalEntity)
        }
    }

    override fun insertToPregnantJournal(journalEntity: PregnantJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            pregnantJLocalDatasource.insertPregnantJournal(journalEntity)
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

    override fun deleteSpecificPregnantJournal(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            pregnantJLocalDatasource.deleteSpecificPregnantJournal(id)
        }
    }

    suspend fun getLastJournal(): KiddoJournalEntity {
        return kiddoJLocalDatasource.getLastJournal()
    }

    //firestore for recipes
    override fun getRecipesData(): LiveData<MutableList<RecipesEntity>> {
        return RecipeFirestoreSrc.getRecipesData()
    }

    override fun getARecipe(judul: String): LiveData<RecipesEntity> {
        return RecipeFirestoreSrc.getARecipe(judul)
    }

    override fun getRecipesByBahan(bahan: String): LiveData<MutableList<RecipesEntity>> {
        return RecipeFirestoreSrc.getRecipesByBahan(bahan);
    }

    //firestore for Imunisasi
    override fun getImunisasiData(): LiveData<MutableList<ImunisasiEntity>> {
        return ImunisasiFirestoreSrc.getImunisasiData()
    }

    //Journal Pregnant
    override suspend fun getLastPregnantJournal(): PregnantJournalEntity {
        return pregnantJLocalDatasource.getLastPregnantJournal()
    }

    // Journal Asi
    override  suspend fun getAllAsiJournal(): List<AsiJournalEntity> {
        return asiJLocalDataSource.getAllAsiJournal()
    }

    override suspend fun getJournalByBulan(bulan: String): String {
        return asiJLocalDataSource.getJournalByBulan(bulan)
    }

    override fun insertAsiJournal(asiJournalEntity: AsiJournalEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            asiJLocalDataSource.insertAsiJournal(asiJournalEntity)
        }
    }

}