package com.mellagusty.hacigo_mobileapp.data.local.knowledgebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KnowledgebaseEntity (
    val title_kb : String? = null,
    val meta_kb : String? = null,
    val isi : String? = null
): Parcelable