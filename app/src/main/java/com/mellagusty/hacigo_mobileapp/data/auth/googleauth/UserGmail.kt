package com.mellagusty.hacigo_mobileapp.data.auth.googleauth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserGmail(

    val id: String? = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val location: String? = "",
    val age: Int? = 0,
    val pregnant: String? = "",
    val profileComplete: Int? = 0

): Parcelable
