package com.mellagusty.hacigo_mobileapp.data.local.event

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventEntity(
    val title_event : String? = null,
    val noteSpeaker : String? = null,
    val schedule : String? = null,
    val heldOn : String? = null,
    val image : Int? = null
): Parcelable
