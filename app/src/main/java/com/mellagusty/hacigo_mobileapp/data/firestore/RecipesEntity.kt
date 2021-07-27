package com.mellagusty.hacigo_mobileapp.data.firestore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipesEntity(
    val judul: String? = null,
    val subJudul: String? = null,
    val bahan: ArrayList<String>,
    val caraBuat: ArrayList<String>,
    val imageUrl: String? = null


) : Parcelable

