package com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImunisasiEntity (
var bulan: Int? = null,
var jenisImunisasi: ArrayList<String>? = null,
var status_imunisasi: String? = null

): Parcelable