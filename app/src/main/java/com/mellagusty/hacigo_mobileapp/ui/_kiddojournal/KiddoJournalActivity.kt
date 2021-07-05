package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoJournalBinding

class KiddoJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKiddoJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }


    }
}