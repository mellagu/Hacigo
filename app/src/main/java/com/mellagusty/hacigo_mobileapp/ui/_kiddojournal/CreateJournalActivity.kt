package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityCreateJournalBinding
import com.mellagusty.hacigo_mobileapp.di.HacigoDataInjection
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*

class CreateJournalActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private lateinit var binding: ActivityCreateJournalBinding
    private lateinit var repository: Repository
    private lateinit var viewModel: CreateJournalViewModel

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

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(CreateJournalViewModel::class.java)


        if (noteId != -1)
            CoroutineScope(Dispatchers.IO).launch{
                var journal = viewModel.getSpecificAllJournal(noteId)
                binding.colorView.setBackgroundColor(Color.parseColor(journal.color))
                binding.colorView2.setBackgroundColor(Color.parseColor(journal.color))
                binding.colorView3.setBackgroundColor(Color.parseColor(journal.color))
                binding.colorView4.setBackgroundColor(Color.parseColor(journal.color))
                binding.etJournalTitle.setText(journal.title)
                binding.etJournalSubTitle.setText(journal.subTitle)
                binding.etJournalDesc.setText(journal.journalText)
                binding.etAge.setText(journal.ageInMonth)
                binding.etWeight.setText(journal.weight)
                binding.etHeight.setText(journal.height)

                if (journal.imgPath != "") {
                    selectedImagePath = journal.imgPath!!
                    binding.imgJournal.setImageBitmap(BitmapFactory.decodeFile(journal.imgPath))
                    binding.layoutImage.visibility = View.VISIBLE
                    binding.imgJournal.visibility = View.VISIBLE
                    binding.imgDelete.visibility = View.VISIBLE
                } else {
                    binding.layoutImage.visibility = View.GONE
                    binding.imgJournal.visibility = View.GONE
                    binding.imgDelete.visibility = View.GONE
                }

                if (journal.webLink != "") {
                    webLink = journal.webLink!!
                    binding.tvWebLink.text = journal.webLink
                    binding.layoutWebUrl.visibility = View.VISIBLE
                    binding.etWebLink.setText(journal.webLink)
                    binding.imgUrlDelete.visibility = View.VISIBLE
                } else {
                    binding.imgUrlDelete.visibility = View.GONE
                    binding.layoutWebUrl.visibility = View.GONE
                }


            }
        
        noteId = intent.getIntExtra("noteId",-1)

//        LocalBroadcastManager.getInstance(this).registerReceiver(
//            BroadcastReceiver, IntentFilter("bottom_sheet_action")
//        )

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

        currentDate = sdf.format(Date())
        binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

        binding.tvDateTime.text = currentDate

        binding.saveChecklist.setOnClickListener {
            if (noteId != -1){
                updateJournal()
            } else
            saveJournal()
        }
        binding.back.setOnClickListener {
            onBackPressed()
        }
        
    }



    private fun updateJournal() {
//        var journal = KiddoJournalEntity()
//        lifecycleScope.launch {
//            viewModel.updateJournal(journal)
//        }

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

//            repository.insertToJournal(journal)
            lifecycleScope.launch {
                viewModel.insertToJournal(journal)
            }
            binding.etJournalTitle.setText("")
            binding.etJournalSubTitle.setText("")
            binding.etJournalDesc.setText("")
            binding.etAge.setText("")
            binding.etHeight.setText("")
            binding.etWeight.setText("")
            binding.layoutImage.visibility = View.GONE
            binding.imgJournal.visibility = View.GONE
            binding.tvWebLink.visibility = View.GONE

            val intent = Intent(this,KiddoJournalActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
//            finish()
//            supportFragmentManager.popBackStack()


        }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }
}

