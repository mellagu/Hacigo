package com.mellagusty.hacigo_mobileapp.ui.account

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmailFirestore
import com.mellagusty.hacigo_mobileapp.databinding.ActivityEditProfileBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    lateinit var userDetail: UserEmail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDetail = UserEmail()

        retrieveData()
        saveData()
    }

    /** Retrieve Data from Login Activity to Profile **/
    private fun retrieveData() {
        if (intent.hasExtra(Constant.EXTRA_USER_DETAIL)) {
            userDetail = intent.getParcelableExtra(Constant.EXTRA_USER_DETAIL)!!
        }
        //set the retrieving data to the ui and make it disable
        binding.etFirstName.isEnabled = false
        binding.etFirstName.setText(userDetail.firstName)

        binding.etLastName.isEnabled = false
        binding.etLastName.setText(userDetail.lastName)

        binding.etEmail.isEnabled = false
        binding.etEmail.setText(userDetail.email)

    }

    /** For the next step after login **/
    private fun validateUserProfileData(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etAge.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan usia anda",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            TextUtils.isEmpty(binding.etKidName.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan nama anak anda",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

            else -> {
                true

            }
        }
    }

    /** Save data **/
    private fun saveData() {
        binding.btnSubmitProfile.setOnClickListener {
            if (validateUserProfileData()) {
                val userHashMap = HashMap<String, Any>()

                //NON DEFAULT DATA THAT WILL BE SAVE IN FIRESTORE
                val ageMom = binding.etAge.text.toString().trim { it <= ' ' }
                if (ageMom.isNotEmpty()) {
                    userHashMap[Constant.AGE] = ageMom.toInt()
                }

                val kidName = binding.etKidName.text.toString().trim { it <= ' ' }
                if (kidName.isNotEmpty()) {
                    userHashMap[Constant.KID_NAME] = kidName
                }

                val kidGender = if (binding.rbMale.isChecked) {
                    Constant.MALE
                } else {
                    Constant.FEMALE
                }
                userHashMap[Constant.GENDER] = kidGender

                val pregnant = if (binding.rbNo.isChecked) {
                    Constant.NOT_PREGNANT
                } else {
                    Constant.YES_PREGNANT
                }
                userHashMap[Constant.PREGNANT] = pregnant

                //must be process dialogue
                /**call function of updateProfile from Firestoreclass and connect it with HashMap to
                update the detail data in firestore**/
                UserEmailFirestore().updateUserProfileData(this, userHashMap)

            }
        }
    }

    fun userProfileUpdateSuccess() {
        Toast.makeText(this, "Profile successfully update", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}