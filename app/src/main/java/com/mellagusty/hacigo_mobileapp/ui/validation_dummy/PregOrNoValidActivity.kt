package com.mellagusty.hacigo_mobileapp.ui.validation_dummy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityPregOrNoValidBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity

class PregOrNoValidActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPregOrNoValidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregOrNoValidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvMomPregnant.setOnClickListener {
            val intent = Intent(this, PregnantFieldActivity::class.java)
            startActivity(intent)
        }

        binding.cvMomKiddo.setOnClickListener {
            val intent = Intent(this, KiddoFieldActivity::class.java)
            startActivity(intent)
        }
    }
}