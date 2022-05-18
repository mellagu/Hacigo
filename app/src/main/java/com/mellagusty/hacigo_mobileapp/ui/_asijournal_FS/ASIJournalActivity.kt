package com.mellagusty.hacigo_mobileapp.ui._asijournal_FS

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.adapter.ASIJournalFSAdapter
import com.mellagusty.hacigo_mobileapp.databinding.ActivityAsijournalBinding

class ASIJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsijournalBinding
    private lateinit var adapterASI: ASIJournalFSAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsijournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAsi.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapterASI = ASIJournalFSAdapter {
            showAsiDialog(it.bulan)
        }
        binding.rvAsi.adapter = adapterASI

        getListASIJournal()
    }

    private fun getListASIJournal() {

        //TODO: RETRIEVE DATA FROM FIRESTORE

    }

    private fun showAsiDialog(bulan: Int?) {
        val builder = AlertDialog.Builder(this, 0)
        builder.setTitle("ASI Eksklusif")
        builder.setMessage("Apakah pada bulan ini Anda telah menerapkan ASI Eksklusif?")

        builder.setPositiveButton("Ya") {
            dialog, id ->

            //TODO: PUSH DATA FROM FIRESTORE
        }

        builder.setNegativeButton("Tidak") {
            dialog, id ->

            //TODO: PUSH DATA FROM FIRESTORE

        }

    }
}
