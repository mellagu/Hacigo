package com.mellagusty.hacigo_mobileapp.ui._asijournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.adapter.AsiJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityAsiJournalBinding
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.PregnantJournalViewModel
import com.mellagusty.hacigo_mobileapp.ui.knowledgebase.KnowledgebaseDetailActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AsiJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsiJournalBinding
    private lateinit var asiJournalAdapter: AsiJournalAdapter
    private lateinit var viewModel: AsiJournalViewModel
    var totalMonth = 0
    private var list: MutableList<AsiJournalEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAsiJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AsiJournalViewModel::class.java)

        binding.rvAsi.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        asiJournalAdapter = AsiJournalAdapter {
            showAsiDialog(it.bulanke)

        }

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.rvAsi.adapter = asiJournalAdapter

        getListAsiJournal()
    }

    fun countTheMonth() {


    }

    private fun getListAsiJournal() {
        val newList: MutableList<AsiJournalEntity> = ArrayList()

        CoroutineScope(Dispatchers.IO).launch {
            for ( i in 1..6 ) {
                val asi = viewModel.getJournalByBulan(i.toString())
                val journal = AsiJournalEntity()

                journal.bulanke = i

                journal.asi = asi
                Log.d("asijournal","asi, list ke-$i")
                newList.add(journal)

                Log.d("isi journal", "asi, isi journal : $journal, journalasi = ${journal.asi}, journalbulanke = ${journal.bulanke}")
            }
//            Log.d("newlist","asi, newlist : $newList2")
//            print(newList2)
            asiJournalAdapter.setListData(newList)
            binding.rvAsi.adapter = asiJournalAdapter
        }
    }

    private fun showAsiDialog(bulanke: Int? ) {
        val builder = AlertDialog.Builder(this, 0)

        builder.setTitle("ASI Eksklusif")

        builder.setMessage("Apakah pada bulan ini Anda telah menerapkan ASI Eksklusif?")

        builder.setPositiveButton( "Ya" ) {
                dialog, id ->
            totalMonth += 1
            Log.d("check","total month : $totalMonth")
            //TODO: save total month to sharePreferences/Data Store
//            var asi = AsiJournalEntity()
//            asi.bulanke = bulanke
//            asi.asi = "ya"
//            lifecycleScope.launch {
//                viewModel.insertAsiJournal(asi)
//            }
//            Toast.makeText(this, "sudah :)", Toast.LENGTH_SHORT).show()
//            finish()
//            startActivity(getIntent())
        }

        builder.setNegativeButton( "Tidak" ) {
                dialog, id ->
            var asi = AsiJournalEntity()
            asi.bulanke = bulanke
            asi.asi = "tidak"
            lifecycleScope.launch {
                viewModel.insertAsiJournal(asi)
            }
            Toast.makeText(this, "belum :(", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(getIntent())
        }

        builder.show()
    }
}