package com.mellagusty.hacigo_mobileapp.ui._imunisasijournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityImunisasiJournalBinding

class ImunisasiJournalActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImunisasiJournalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImunisasiJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}