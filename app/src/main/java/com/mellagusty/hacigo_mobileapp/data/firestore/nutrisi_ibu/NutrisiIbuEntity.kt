package com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrisiIbuEntity(
    var imageUrl: String? = null,
    var judul: String? = null,
    var p0: ArrayList<String>? = null,
    var p0title: String? = null,
    var p1: ArrayList<String>? = null,
    var p1title: String? = null,


) : Parcelable