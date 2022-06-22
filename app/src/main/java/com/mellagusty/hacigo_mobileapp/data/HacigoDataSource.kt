package com.mellagusty.hacigo_mobileapp.data

import androidx.lifecycle.LiveData
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiEntity
import com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu.NutrisiIbuEntity
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity

interface HacigoDataSource {

    //For Remote One
    //Journal
    suspend fun getJournalAll(): List<KiddoJournalEntity>

    suspend fun getAllPregnantJournal(): List<PregnantJournalEntity>

    suspend fun getSpecificAllJournal(id: Int): KiddoJournalEntity

    suspend fun getSpecificAllPregnantJournal(id: Int): PregnantJournalEntity

    suspend fun getLastPregnantJournal(): PregnantJournalEntity

    //Add Delete Update for Local Journal
    fun insertToJournal(journalEntity: KiddoJournalEntity)

    fun insertToPregnantJournal(journalEntity: PregnantJournalEntity)

    fun deleteSpecificJournal(id:Int)

    fun deleteSpecificPregnantJournal(id:Int)

    fun updateJournal(journalEntity: KiddoJournalEntity)

    //For Firestore
    //Recipes
    fun getRecipesData(): LiveData<MutableList<RecipesEntity>>

    fun getARecipe(judul: String): LiveData<RecipesEntity>

    fun getRecipeSixToTen(): LiveData<MutableList<RecipesEntity>>

    fun getRecipeElevenToEighteen(): LiveData<MutableList<RecipesEntity>>

    fun getRecipeNineteentoTwenfour(): LiveData<MutableList<RecipesEntity>>

    fun getRecipesByBahan(bahan : String): LiveData<MutableList<RecipesEntity>>

    //Imunisasi
    fun getImunisasiData(): LiveData<MutableList<ImunisasiEntity>>

    //Nutrisi
    fun getNutritionData(): LiveData<MutableList<NutrisiIbuEntity>>
    fun getANutrition(judul: String): LiveData<NutrisiIbuEntity>

    // Asi Journal

    suspend fun getAllAsiJournal(): List<AsiJournalEntity>

    suspend fun getJournalByBulan(bulan: String): String

    fun insertAsiJournal(asiJournalEntity: AsiJournalEntity)
}