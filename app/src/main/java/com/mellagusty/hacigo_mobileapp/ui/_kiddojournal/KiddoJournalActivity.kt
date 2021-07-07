package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.KiddoJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoJournalBinding
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KiddoJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoJournalBinding
    private lateinit var repository: Repository
    private lateinit var kiddoJournalAdapter : KiddoJournalAdapter
    private var list: List<KiddoJournalEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKiddoJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kiddoJournalAdapter = KiddoJournalAdapter{}


        repository = HacigoDataInjection().provideRepository(this)

        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this,CreateJournalActivity::class.java)
            startActivity(intent)
        }

        getAllJournal()

    }

    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = repository.getJournalAll()
            kiddoJournalAdapter.setListData(journal)
            list = journal as ArrayList<KiddoJournalEntity>
            binding.recyclerView.adapter = kiddoJournalAdapter
        }
    }
}