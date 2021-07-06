package com.mellagusty.hacigo_mobileapp.data.local

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface KiddoJournalDao {

    @Query("SELECT * FROM KiddoJournal ORDER BY id DESC")
    suspend fun getAllJournal(): List<KiddoJournalEntity>

    @Query("SELECT * FROM KiddoJournal WHERE id =:id")
    suspend fun getSpecificJournal(id:Int) : KiddoJournalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(journalEntity: KiddoJournalEntity)

    @Query("DELETE FROM KiddoJournal WHERE id =:id")
    suspend fun deleteSpecificJournal(id:Int)

    @Update
    suspend fun updateJournal(journalEntity: KiddoJournalEntity)

    @Delete
    suspend fun deleteJournal(journalEntity: KiddoJournalEntity)




}