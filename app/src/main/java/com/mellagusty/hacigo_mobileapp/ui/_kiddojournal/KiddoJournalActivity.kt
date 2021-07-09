package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.KiddoJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityKiddoJournalBinding
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KiddoJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKiddoJournalBinding
    private lateinit var kiddoJournalAdapter : KiddoJournalAdapter
    private lateinit var viewModel: KiddoJournalViewModel

    private var list: List<KiddoJournalEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKiddoJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(KiddoJournalViewModel::class.java)

        kiddoJournalAdapter = KiddoJournalAdapter{
            Toast.makeText(this, "Nilai anda ${it.title}", Toast.LENGTH_SHORT).show()
        }


//        repository = HacigoDataInjection().provideRepository(this)

        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this,CreateJournalActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        
        getAllJournal()

    }

//    override fun onResume() {
//        super.onResume()
//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.getJournalAll()
//        }
//    }

    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = viewModel.getJournalAll()
            kiddoJournalAdapter.setListData(journal)
            list = journal as ArrayList<KiddoJournalEntity>
            binding.recyclerView.adapter = kiddoJournalAdapter
        }
    }
}