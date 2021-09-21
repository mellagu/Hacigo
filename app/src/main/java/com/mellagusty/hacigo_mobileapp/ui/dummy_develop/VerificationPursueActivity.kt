package com.mellagusty.hacigo_mobileapp.ui.dummy_develop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.databinding.ActivityDummyDevelopBinding
import com.mellagusty.hacigo_mobileapp.databinding.ActivityVerificationPursueBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui.auth.emailLoginActivity

class VerificationPursueActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVerificationPursueBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationPursueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, emailLoginActivity::class.java)
            startActivity(intent)
        }
    }
}