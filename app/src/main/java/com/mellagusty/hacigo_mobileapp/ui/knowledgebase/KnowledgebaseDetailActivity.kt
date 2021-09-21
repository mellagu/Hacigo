package com.mellagusty.hacigo_mobileapp.ui.knowledgebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKnowledgebaseDetailBinding

class KnowledgebaseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKnowledgebaseDetailBinding

    companion object {
        const val EXTRA_TITLE_KB = "extra_title_kb"
        const val EXTRA_ISI_KB = "extra_isi_kb"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKnowledgebaseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE_KB)
        val isi = intent.getStringExtra(EXTRA_ISI_KB)

        binding.tvTitleKb.text = title
        binding.tvIsiKb.text = isi

        binding.arrowBack.setOnClickListener{
            onBackPressed()
        }
    }
}