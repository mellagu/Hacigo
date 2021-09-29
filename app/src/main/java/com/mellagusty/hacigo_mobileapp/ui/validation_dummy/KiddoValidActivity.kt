package com.mellagusty.hacigo_mobileapp.ui.validation_dummy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoValidBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity

class KiddoValidActivity : AppCompatActivity() {
    private lateinit var binding : ActivityKiddoValidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKiddoValidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvUptoSix.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}