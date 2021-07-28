package com.mellagusty.hacigo_mobileapp.data.firestore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipesEntity(
    val judul: String? = null,
    val subJudul: String? = null,
    val bahanUtama: ArrayList<String>,
    val nutrisi: String? = null,
    val bahanSemua: ArrayList<String>,
    val caraBuat: ArrayList<String>,
    val imageUrl: Int


) : Parcelable

