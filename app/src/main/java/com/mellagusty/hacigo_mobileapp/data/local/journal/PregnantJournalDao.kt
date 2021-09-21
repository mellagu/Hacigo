package com.mellagusty.hacigo_mobileapp.data.local.journal

import androidx.room.*

@Dao
interface PregnantJournalDao {

    // DAO for Pregnant
    @Query("SELECT * FROM PregnantJournal ORDER BY id DESC")
    suspend fun getAllPregnantJournal(): List<PregnantJournalEntity>

    //this is for edit Journal, you have to pick Journal
    @Query("SELECT * FROM PregnantJournal WHERE id =:id")
    suspend fun getSpecificPregnantJournal(id:Int) : PregnantJournalEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPregnantJournal(journalEntity: PregnantJournalEntity)

    @Query("DELETE FROM PregnantJournal WHERE id =:id")
    suspend fun deleteSpecificPregnantJournal(id:Int)

    @Update
    suspend fun updatePregnantJournal(journalEntity: PregnantJournalEntity)

    @Delete
    suspend fun deletePregnantJournal(journalEntity: PregnantJournalEntity)

    // this is to get the last journal
    @Query("SELECT * FROM PregnantJournal ORDER BY id DESC LIMIT 1")
    suspend fun getLastPregnantJournal(): PregnantJournalEntity


}