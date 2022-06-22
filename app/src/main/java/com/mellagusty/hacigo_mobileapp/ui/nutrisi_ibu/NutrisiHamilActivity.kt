package com.mellagusty.hacigo_mobileapp.ui.nutrisi_ibu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.adapter.NutrisiHamilAdapter
import com.mellagusty.hacigo_mobileapp.databinding.ActivityNutrisiHamilBinding
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.PregnantJournalActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class NutrisiHamilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNutrisiHamilBinding
    private lateinit var adapter: NutrisiHamilAdapter
    private lateinit var viewModel: NutrisiIbuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutrisiHamilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(NutrisiIbuViewModel::class.java)

        viewModel.fetchNutritionData().observe(this, Observer {
            binding.rvNutrition.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            adapter = NutrisiHamilAdapter {
                val intent = Intent(this, NutrisiHamilDetailActivity::class.java)
                intent.putExtra(NutrisiHamilDetailActivity.EXTRA_JUDUL, it.judul)
                startActivity(intent)
            }
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
            binding.rvNutrition.adapter = adapter

            Log.d("this is", "ini data Anda untuk fragment$it")
        })

        binding.arrowBack.setOnClickListener {
            startActivity(Intent(this, PregnantJournalActivity::class.java))
        }

    }
}