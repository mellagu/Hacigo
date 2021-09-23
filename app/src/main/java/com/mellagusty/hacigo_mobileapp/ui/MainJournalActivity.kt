package com.mellagusty.hacigo_mobileapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.databinding.ActivityMainJournalBinding
import com.mellagusty.hacigo_mobileapp.ui._asijournal.AsiJournalActivity
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalActivity
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.PregnantJournalActivity

class MainJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.cvJournalKiddo.setOnClickListener{
            val intent = Intent(this, KiddoJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvJournalAsi.setOnClickListener{
            val intent = Intent(this, AsiJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvJournalPregnant.setOnClickListener{
            val intent = Intent(this, PregnantJournalActivity::class.java)
            startActivity(intent)
        }

    }
}