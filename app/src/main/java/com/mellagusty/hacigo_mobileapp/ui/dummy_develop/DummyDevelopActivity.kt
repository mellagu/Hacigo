package com.mellagusty.hacigo_mobileapp.ui.dummy_develop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityDummyDevelopBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity

class DummyDevelopActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDummyDevelopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDummyDevelopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}