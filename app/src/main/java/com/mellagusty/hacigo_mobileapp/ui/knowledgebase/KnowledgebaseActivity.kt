package com.mellagusty.hacigo_mobileapp.ui.knowledgebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mellagusty.hacigo_mobileapp.adapter.KnowledgebaseAdapter
import com.mellagusty.hacigo_mobileapp.data.local.knowledgebase.KnowledgebaseEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKnowledgebaseBinding
import com.mellagusty.hacigo_mobileapp.ui._parenthood.DummyArticle

class KnowledgebaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKnowledgebaseBinding
    private lateinit var kbAdapter: KnowledgebaseAdapter
//    private lateinit var kbViewModel: KnowledgebaseViewModel

    private var kblist = mutableListOf<KnowledgebaseEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKnowledgebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.rvKnowledgebase.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        kbAdapter = KnowledgebaseAdapter {
            val intent = Intent(this, KnowledgebaseDetailActivity::class.java)
            intent.putExtra(KnowledgebaseDetailActivity.EXTRA_TITLE_KB, it.title_kb)
            intent.putExtra(KnowledgebaseDetailActivity.EXTRA_ISI_KB, it.isi)
            startActivity(intent)
        }
        binding.rvKnowledgebase.adapter = kbAdapter

        initDummyDataKb()
    }

    private fun initDummyDataKb() {
        kblist.clear()
        kblist = DummyKnowledgebase()
        kbAdapter.setListData(kblist)
    }
}