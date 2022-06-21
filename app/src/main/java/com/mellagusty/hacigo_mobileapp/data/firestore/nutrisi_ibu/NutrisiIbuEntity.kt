package com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrisiIbuEntity(
    var p0title: String? = null,
    var p1title: String? = null,
    var p0: ArrayList<String>? = null,
    var p1: ArrayList<String>? = null,
    var imageUrl: String? = null

) : Parcelable