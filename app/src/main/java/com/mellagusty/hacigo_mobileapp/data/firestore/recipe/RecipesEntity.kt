package com.mellagusty.hacigo_mobileapp.data.firestore.recipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipesEntity(
    var judul: String? = null,
    var subJudul: String? = null,
    var faktaNutrisi: String? = null,
    var bahan: ArrayList<String>?  = null,
    var caraBuat : ArrayList<String>?  = null,
    var imageUrl : String? = null,
    var usia : Int? = null


) : Parcelable

