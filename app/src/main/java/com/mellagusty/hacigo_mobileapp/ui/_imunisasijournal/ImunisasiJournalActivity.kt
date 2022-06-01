package com.mellagusty.hacigo_mobileapp.ui._imunisasijournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.ImunisasiJournalAdapter
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityImunisasiJournalBinding
import com.mellagusty.hacigo_mobileapp.utils.Constant
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import java.util.*

class ImunisasiJournalActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImunisasiJournalBinding
    private lateinit var imunisasiJournalAdapter: ImunisasiJournalAdapter
    private lateinit var viewModel: ImunisasiJournalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImunisasiJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(ImunisasiJournalViewModel::class.java)

        binding.rvImunisasi.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        imunisasiJournalAdapter = ImunisasiJournalAdapter{
            showImunisasiDialog(it.bulan,it.jenisImunisasi)
        }

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.rvImunisasi.adapter = imunisasiJournalAdapter

        getAllImunisasiData()


    }

    private fun getAllImunisasiData() {
        viewModel.fetchImunisasiData().observe(this){
            Log.d("tag","bulan ke ${it.get(0)}")
            binding.rvImunisasi.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//            imunisasiJournalAdapter = ImunisasiJournalAdapter{
//                showImunisasiDialog(it.bulan,it.jenisImunisasi)
//            }
            imunisasiJournalAdapter.setListData(it)
//            imunisasiJournalAdapter.notifyDataSetChanged()
            binding.rvImunisasi.adapter = imunisasiJournalAdapter

            Log.d("this is", "ini data Anda untuk fragment$it")
        }
    }
    private fun showImunisasiDialog(bulan: Int?, jenisImunisasi:ArrayList<String>?) {
        val uid = FirebaseAuth.getInstance().uid.toString()
        val builder = AlertDialog.Builder(this,0)
        builder.setTitle("Imunisasi")
        builder.setMessage("Apakah pada bulan ke-$bulan anda telah memberikan imunisasi?")

        builder.setPositiveButton("Ya"){dialog, id ->
            val yes_imunisasi = ImunisasiEntity(bulan = bulan, status_imunisasi = Constant.YA, jenisImunisasi = jenisImunisasi )
            Log.d("tag","Imunisasi $yes_imunisasi")



            FirebaseFirestore.getInstance().collection(Constant.IMUNISASI)
                .document("$bulan")
                .set(yes_imunisasi)
                .addOnSuccessListener {
                    Log.d("tag","Imunisasi masuk : $yes_imunisasi")
//                    imunisasiJournalAdapter.setStatusImunisasi(statusImunisasi = Constant.YA)
                    Toast.makeText(this, "Imunisasi bulan ke-$bulan sudah dilakukan", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(getIntent())
                } .addOnFailureListener {
                    Log.d("tag","Imunisasi gagal : $yes_imunisasi")
                }
            imunisasiJournalAdapter.notifyDataSetChanged()

        }
        builder.setNegativeButton("Tidak"){dialog, id ->
            val no_imunisasi = ImunisasiEntity(bulan = bulan, status_imunisasi = Constant.TIDAK)
            Log.d("tag","Tidak Imunisasi $no_imunisasi")


            FirebaseFirestore.getInstance().collection(Constant.USERS)
                .document(uid)
                .collection(Constant.IMUNISASI)
                .document("$bulan")
                .set(no_imunisasi)
                .addOnSuccessListener {
                    Log.d("tag","Imunisasi masuk : $no_imunisasi")
//                    imunisasiJournalAdapter.setStatusImunisasi(statusImunisasi = Constant.TIDAK)
                    Toast.makeText(this, "Imunisasi bulan ke-$bulan tidak dilakukan", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(getIntent())
                } .addOnFailureListener {
                    Log.d("tag","Imunisasi gagal : $no_imunisasi")
                }
            imunisasiJournalAdapter.notifyDataSetChanged()
        }
        builder.show()
    }


}