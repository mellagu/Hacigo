package com.mellagusty.hacigo_mobileapp.data.local.journal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsiJournalDao {

    @Query("SELECT * FROM AsiJournal ORDER BY bulanke ASC")
    suspend fun getAllAsiJournal(): List<AsiJournalEntity>

    @Query("SELECT asi FROM AsiJournal WHERE bulanke =:bulan")
    suspend fun getJournalByBulan(bulan: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsiJournal(asiJournalEntity: AsiJournalEntity)
}