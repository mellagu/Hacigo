package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mellagusty.hacigo_mobileapp.adapter.KiddoJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoJournalBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KiddoJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoJournalBinding
    private lateinit var kiddoJournalAdapter: KiddoJournalAdapter
    private lateinit var viewModel: KiddoJournalViewModel
    private var noteId = -1

    private var list: List<KiddoJournalEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKiddoJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(KiddoJournalViewModel::class.java)

        kiddoJournalAdapter = KiddoJournalAdapter {
            val intent = Intent(this,CreateJournalActivity::class.java)
            intent.putExtra("note_id",it.id!!)
            startActivity(intent)
        }

//        repository = HacigoDataInjection().provideRepository(this)

        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this, CreateJournalActivity::class.java)
            intent.putExtra("note_id",-1)
            startActivity(intent)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerView.adapter = kiddoJournalAdapter


        getAllJournal()

    }


    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = viewModel.getJournalAll()
            kiddoJournalAdapter.setListData(journal)
            list = journal as ArrayList<KiddoJournalEntity>
            binding.recyclerView.adapter = kiddoJournalAdapter
        }
    }
}