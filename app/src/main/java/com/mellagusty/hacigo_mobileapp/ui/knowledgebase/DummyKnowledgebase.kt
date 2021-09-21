package com.mellagusty.hacigo_mobileapp.ui.knowledgebase

import com.mellagusty.hacigo_mobileapp.data.local.knowledgebase.KnowledgebaseEntity

fun DummyKnowledgebase(): MutableList<KnowledgebaseEntity> {
    val kb = mutableListOf<KnowledgebaseEntity>()

    kb.apply {
        add(
            KnowledgebaseEntity(
                "Panduan Usia 0-6 bulan",
                "Pahami kebutuhan anak yang baru lahir",
                "Ini adalah isi"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia 6-24 bulan",
                "Pahami kebutuhan anak yang sudah mulai besar",
                "Ini adalah isi 6-24"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia >2 tahun",
                "Pahami kebutuhan balita",
                "Ini adalah isi >2"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia >5 tahun",
                "Pahami kebutuhan anak yang sudah makin gede",
                "Ini adalah isi >5"
            )
        )

    }
    return kb
}