package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityCreateJournalBinding
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection

class CreateJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateJournalBinding
    private lateinit var repository: Repository

    var selectedColor = "#DD4F8A"
    var currentDate: String? = null
    private var READ_STORAGE_PERM = 123
    private var REQUEST_CODE_IMAGE = 456
    private var selectedImagePath = ""
    private var webLink = ""
    private var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = HacigoDataInjection().provideRepository(this)


        binding.saveChecklist.setOnClickListener {
            saveJournal()
        }
        binding.back.setOnClickListener {
            onBackPressed()
        }
        
    }


    fun saveJournal() {

            var journal = KiddoJournalEntity()
            journal.title = binding.etJournalTitle.text.toString()
            journal.subTitle = binding.etJournalSubTitle.text.toString()
            journal.journalText = binding.etJournalDesc.text.toString()
            journal.ageInMonth = binding.etAge.text.toString()
            journal.weight = binding.etWeight.text.toString()
            journal.height = binding.etHeight.text.toString()
            journal.dateTime = currentDate
            journal.color = selectedColor
            journal.imgPath = selectedImagePath
            journal.webLink = webLink

            repository.insertToJournal(journal)
            binding.etJournalTitle.setText("")
            binding.etJournalSubTitle.setText("")
            binding.etJournalDesc.setText("")
            binding.etAge.setText("")
            binding.etHeight.setText("")
            binding.etWeight.setText("")
            binding.layoutImage.visibility = View.GONE
            binding.imgJournal.visibility = View.GONE
            binding.tvWebLink.visibility = View.GONE
            supportFragmentManager.popBackStack()

        }
    }

