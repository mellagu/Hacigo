package com.mellagusty.hacigo_mobileapp.data.firestore.asi_journal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ASIJournalEntity(
    val id : String = "",
    var bulan : Int? = null,
    var asi : String? = null

):Parcelable