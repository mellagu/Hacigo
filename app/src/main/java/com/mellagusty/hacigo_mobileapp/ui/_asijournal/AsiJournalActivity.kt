package com.mellagusty.hacigo_mobileapp.ui._asijournal

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.adapter.AsiJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityAsiJournalBinding
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
//            val intent = Intent(this, KnowledgebaseDetailActivity::class.java)
//            startActivity(intent)
            showAsiDialog(it.bulanke)

        }

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.rvAsi.adapter = asiJournalAdapter

        getListAsiJournal()
    }


    fun getListAsiJournal() {
        val newList: MutableList<AsiJournalEntity> = ArrayList()

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..6) {
                val asi = viewModel.getJournalByBulan(i.toString())
                var journal = AsiJournalEntity()

                journal.bulanke = i

                if (asi != null) {
                    journal.asi = asi
                } else {
                    journal.asi = "belum"
                }
                Log.d("asijournal", "asi, list ke-$i")
                newList.add(journal)

                Log.d(
                    "isi journal",
                    "asi, isi journal : $journal, journalasi = ${journal.asi}, journalbulanke = ${journal.bulanke}"
                )
            }

            asiJournalAdapter.setListData(newList)
            binding.rvAsi.adapter = asiJournalAdapter
        }
    }

    fun showAsiDialog(bulanke: Int?) {
        val builder = AlertDialog.Builder(this, 0)

        builder.setTitle("ASI Eksklusif")

        builder.setMessage("Apakah pada bulan ini Anda telah menerapkan ASI Eksklusif?")

        builder.setPositiveButton("Ya") { dialog, id ->
            totalMonth += 1
            Log.d("check", "total month : $totalMonth")

            var asi = AsiJournalEntity()
            asi.bulanke = bulanke
            asi.asi = "ya"
            lifecycleScope.launch {
                viewModel.insertAsiJournal(asi)
            }
            Toast.makeText(this, "sudah :)", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(getIntent())
        }

        builder.setNegativeButton("Tidak") { dialog, id ->
            var asi = AsiJournalEntity()
            asi.bulanke = bulanke
            asi.asi = "tidak"
            lifecycleScope.launch {
                viewModel.insertAsiJournal(asi)
            }
            Toast.makeText(this, "belum :(", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }

        builder.show()
    }
}