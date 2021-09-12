package com.mellagusty.hacigo_mobileapp.ui._pregnantjournal

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mellagusty.hacigo_mobileapp.adapter.PregnantJournalAdapter
import com.mellagusty.hacigo_mobileapp.databinding.ActivityPregnantJournalBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class PregnantJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPregnantJournalBinding
    private lateinit var pregnantJournalAdapter: PregnantJournalAdapter
    private lateinit var viewModel: PregnantJournalViewModel
    private var noteId = -1

    private var list: List<PregnantJournalEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregnantJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PregnantJournalViewModel::class.java)


        pregnantJournalAdapter = PregnantJournalAdapter {
            val intent = Intent(this, CreatePregnantJournalActivity::class.java)
            startActivity(intent)
        }

        //back press
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabBtnCreateJournal.setOnClickListener {
            val intent = Intent(this, CreatePregnantJournalActivity::class.java)
            intent.putExtra("note_id",-1)
            startActivity(intent)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerView.adapter = pregnantJournalAdapter


        searchBar()
        getAllJournal()

    }

    private fun searchBar() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tempArray = ArrayList<PregnantJournalEntity>()
                for (array in list ){
                    if (array.title!!.toLowerCase(Locale.getDefault()).contains(newText.toString())){
                        tempArray.add(array)
                    }
                }
                pregnantJournalAdapter.setListData(tempArray)
                pregnantJournalAdapter.notifyDataSetChanged()
                return true
            }
        })
    }


    private fun getAllJournal() {
        CoroutineScope(Dispatchers.IO).launch {
            var journal = viewModel.getPregnantJournalAll()

            pregnantJournalAdapter.setListData(journal)
            list = journal
            binding.recyclerView.adapter = pregnantJournalAdapter
        }
    }
}