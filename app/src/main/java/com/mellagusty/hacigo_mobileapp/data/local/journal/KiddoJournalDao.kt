package com.mellagusty.hacigo_mobileapp.data.local.journal

import androidx.room.*

@Dao
interface KiddoJournalDao {

    @Query("SELECT * FROM KiddoJournal ORDER BY id DESC")
    suspend fun getAllJournal(): List<KiddoJournalEntity>

    //this is for edit Journal, you have to pick Journal
    @Query("SELECT * FROM KiddoJournal WHERE id =:id")
    suspend fun getSpecificJournal(id:Int) : KiddoJournalEntity

    //this is for edit Pregnant Journal



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(journalEntity: KiddoJournalEntity)

    @Query("DELETE FROM KiddoJournal WHERE id =:id")
    suspend fun deleteSpecificJournal(id:Int)

    @Update
    suspend fun updateJournal(journalEntity: KiddoJournalEntity)

    @Delete
    suspend fun deleteJournal(journalEntity: KiddoJournalEntity)

    // this is to get the last journal
    @Query("SELECT * FROM KiddoJournal ORDER BY id DESC LIMIT 1")
    suspend fun getLastJournal(): KiddoJournalEntity






}