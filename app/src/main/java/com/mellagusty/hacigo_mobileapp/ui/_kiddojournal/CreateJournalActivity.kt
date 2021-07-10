package com.mellagusty.hacigo_mobileapp.ui._kiddojournal

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityCreateJournalBinding
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.util.JournalBottomFragment
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class CreateJournalActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    private lateinit var binding: ActivityCreateJournalBinding
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
        viewModel = ViewModelProvider(this, factory).get(CreateJournalViewModel::class.java)


        if (noteId != -1)
            CoroutineScope(Dispatchers.IO).launch {
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

        noteId = intent.getIntExtra("noteId", -1)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            BroadcastReceiver, IntentFilter("bottom_sheet_action")
        )

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

        currentDate = sdf.format(Date())
        binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

        binding.tvDateTime.text = currentDate

        binding.saveChecklist.setOnClickListener {
            if (noteId != -1) {
                updateJournal()
            } else
                saveJournal()
        }
        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.imgMore.setOnClickListener {
            var journalBottomFragment = JournalBottomFragment.newInstance(noteId)
            journalBottomFragment.show(this.supportFragmentManager, "Journal Bottom Sheet")
        }

        binding.imgDelete.setOnClickListener {
            selectedImagePath = ""
            binding.layoutImage.visibility = View.GONE

        }

        binding.btnOk.setOnClickListener {
            if (binding.etWebLink.text.toString().trim().isNotEmpty()) {
                checkWebUrl()
            } else {
                Toast.makeText(this, "Url is Required", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            if (noteId != -1) {
                binding.tvWebLink.visibility = View.VISIBLE
                binding.layoutWebUrl.visibility = View.GONE
            } else {
                binding.layoutWebUrl.visibility = View.GONE
            }

        }

        binding.imgUrlDelete.setOnClickListener {
            webLink = ""
            binding.tvWebLink.visibility = View.GONE
            binding.imgUrlDelete.visibility = View.GONE
            binding.layoutWebUrl.visibility = View.GONE
        }

        binding.tvWebLink.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.etWebLink.text.toString()))
            startActivity(intent)
        }
    }

    private fun checkWebUrl() {
        if (Patterns.WEB_URL.matcher(binding.etWebLink.text.toString()).matches()){
            binding.layoutWebUrl.visibility = View.GONE
            binding.etWebLink.isEnabled = false
            webLink = binding.etWebLink.text.toString()
            binding.tvWebLink.visibility = View.VISIBLE
            binding.tvWebLink.text = binding.etWebLink.text.toString()
        } else{
            Toast.makeText(this, "Url is not valid", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateJournal() {

        lifecycleScope.launch {
            var journal = viewModel.getSpecificAllJournal(noteId)
            journal.title = binding.etJournalTitle.toString()
            journal.subTitle = binding.etJournalSubTitle.toString()
            journal.ageInMonth = binding.etAge.toString()
            journal.height = binding.etHeight.toString()
            journal.weight = binding.etWeight.toString()
            journal.journalText = binding.etJournalDesc.toString()
            journal.dateTime = currentDate
            journal.color = selectedColor
            journal.imgPath = selectedImagePath
            journal.webLink = webLink

            viewModel.updateJournal(journal)
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

        val intent = Intent(this, KiddoJournalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)


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

        val intent = Intent(this, KiddoJournalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)


    }

    private val BroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

            var actionColor = p1!!.getStringExtra("action")
            when (actionColor!!) {

                "Blue" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Green" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Black" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView3.setBackgroundColor(Color.parseColor(selectedColor))
                    binding.colorView4.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Image" -> {
                    readStorageTask()
                    binding.layoutWebUrl.visibility = View.GONE
                }

                "WebUrl" -> {
                    binding.layoutWebUrl.visibility = View.VISIBLE
                }
                "DeleteNote" -> {
                    //delete note
                    deleteNote()
                }


                else -> {
                    binding.layoutImage.visibility = View.GONE
                    binding.imgJournal.visibility = View.GONE
                    binding.layoutWebUrl.visibility = View.GONE
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }
            }
        }
    }

    private fun deleteNote() {

    }

    private fun hasReadStoragePerm(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }


    private fun readStorageTask() {
        if (hasReadStoragePerm()) {

            pickImageFromGallery()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.storage_permission_text),
                READ_STORAGE_PERM,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun pickImageFromGallery() {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }
    }

    private fun getPathFromUri(contentUri: Uri): String?{
        var filePath: String? = null
        var cursor = this.contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            var index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK){
            if (data != null){
                var selectedImageUrl = data.data
                if (selectedImageUrl != null) {
                    try {
                        var inputStream =
                            this.contentResolver.openInputStream(selectedImageUrl)
                        var bitmap = BitmapFactory.decodeStream(inputStream)
                        binding.imgJournal.setImageBitmap(bitmap)
                        binding.imgJournal.visibility = View.VISIBLE
                        binding.layoutImage.visibility = View.VISIBLE

                        selectedImagePath = getPathFromUri(selectedImageUrl)!!
                    } catch (e: Exception){
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }
}

