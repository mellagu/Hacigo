package com.mellagusty.hacigo_mobileapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mellagusty.hacigo_mobileapp.databinding.ActivityMainJournalBinding
import com.mellagusty.hacigo_mobileapp.ui._asijournal.AsiJournalActivity
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.KiddoJournalActivity
import com.mellagusty.hacigo_mobileapp.ui._pregnantjournal.PregnantJournalActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant

class MainJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras
        val priority = intent?.getString(Constant.PRIORITY,"")
        Log.d("checking","checking data user : $priority")

        when(priority){
            Constant.UNDER_SIX_MONTH -> binding.priorityAsi.visibility = View.VISIBLE
            Constant.UP_SIX_MONTH -> binding.priorityKiddo.visibility = View.VISIBLE
            Constant.YES_PREGNANT -> binding.priorityPregnant.visibility = View.VISIBLE
        }

        binding.arrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.cvJournalKiddo.setOnClickListener{
            val intent = Intent(this, KiddoJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvJournalAsi.setOnClickListener{
            val intent = Intent(this, AsiJournalActivity::class.java)
            startActivity(intent)
        }

        binding.cvJournalPregnant.setOnClickListener{
            val intent = Intent(this, PregnantJournalActivity::class.java)
            startActivity(intent)
        }

    }
}