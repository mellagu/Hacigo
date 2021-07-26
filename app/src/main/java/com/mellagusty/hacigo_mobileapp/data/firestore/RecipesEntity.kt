package com.mellagusty.hacigo_mobileapp.data.firestore

data class RecipesEntity(
    val judul: String? = null,
    val subJudul: String? = null,
    val bahan: ArrayList<String>,
    val caraBuat: ArrayList<String>,
    val imageUrl: String? = null


)

