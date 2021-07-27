package com.mellagusty.hacigo_mobileapp.ui._parenthood

import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.event.EventEntity

fun DummyEvent(): MutableList<EventEntity>{
    val event = mutableListOf<EventEntity>()

    event.apply {
        add(
            EventEntity(
                "WEBINAR : Agar Nutrisi Anak Terpenuhi dan Semangat Belajar",
                "oleh Dr. Rahayu Sintha Sp.A",
                "Jumat, 12 Agustus 2021\n"+"08.00 WIB",
                "Youtube Hacigo Indonesia",
                R.drawable.event1
            )
        )
        add(
            EventEntity(
                "WORKSHOP : Teknik Belajar Seru dan Menyenangkan di masa COVID-19",
                "oleh Fatimah Diamar M.Psi",
                "Rabu, 09 September 2021\n"+"18.00 WIB",
                "Youtube Hacigo Indonesia",
                R.drawable.event2
            )
        )
        add(
            EventEntity(
                "WEBINAR : Manajemen Penggunaan ASI Selama Dua Tahun",
                "oleh Diana Arista S.Tr.Keb",
                "Senin, 03 Oktober 2021\n"+"09.00 WIB",
                "Youtube Hacigo Indonesia",
                R.drawable.event3
            )
        )
    }
    return event
}