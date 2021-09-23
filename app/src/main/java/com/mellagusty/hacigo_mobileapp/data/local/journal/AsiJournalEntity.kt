package com.mellagusty.hacigo_mobileapp.data.local.journal

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "AsiJournal")
class AsiJournalEntity : Serializable {

    @PrimaryKey
    var bulanke: Int? = null

    @ColumnInfo(name = "asi")
    var asi: String? = null
}