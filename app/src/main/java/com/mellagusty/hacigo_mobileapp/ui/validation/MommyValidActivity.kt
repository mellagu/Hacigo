package com.mellagusty.hacigo_mobileapp.ui.validation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmailFirestore
import com.mellagusty.hacigo_mobileapp.databinding.ActivityMommyValidBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant

class MommyValidActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMommyValidBinding
    lateinit var userDetail: UserEmail


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMommyValidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveData()

        //view mommy's name
        val sharedPreferences = getSharedPreferences(Constant.HACIGO_PREFERENCES, Context.MODE_PRIVATE)
        val firstName = sharedPreferences?.getString(Constant.LOGGED_IN_USERNAME, " ")!!
        binding.tvMomName.text = firstName
    }

    private fun saveData() {
        binding.cvMomValidSubmit.setOnClickListener {
            val userHashMap = HashMap<String, Any>()
            //NON DEFAULT DATA THAT WILL BE SAVE IN FIRESTORE
            val ageMom = binding.tvAgeMomField.text.toString().trim{ it<= ' '}
            if (ageMom.isEmpty()){
                userHashMap[Constant.AGE] = ageMom.toInt()
            }
            val location = binding.tvLocationField.text.toString().trim{ it<= ' '}
            if (location.isEmpty()){
                userHashMap[Constant.LOCATION] = location
            }

            UserEmailFirestore().updateUserProfileData(this, userHashMap)


        }
    }

    fun userProfileUpdateSuccess() {
        Toast.makeText(this, "Profile successfully update", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}