package com.mellagusty.hacigo_mobileapp.data.local.article

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleEntity (
    val title: String? = null,
    val writer: String? = null,
    val desc: String? = null,
    val image: Int? = null
) : Parcelable