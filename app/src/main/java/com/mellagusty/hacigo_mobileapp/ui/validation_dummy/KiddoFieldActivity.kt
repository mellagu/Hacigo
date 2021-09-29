package com.mellagusty.hacigo_mobileapp.ui.validation_dummy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoFieldBinding

class KiddoFieldActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoFieldBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKiddoFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvKiddoIdentityButton.setOnClickListener {
            val intent = Intent(this, KiddoValidActivity::class.java)
            startActivity(intent)
        }




    }
}