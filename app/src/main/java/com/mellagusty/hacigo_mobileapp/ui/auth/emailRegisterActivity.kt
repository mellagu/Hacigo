package com.mellagusty.hacigo_mobileapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmailFirestore
import com.mellagusty.hacigo_mobileapp.databinding.ActivityEmailRegisterBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidFragment
import com.mellagusty.hacigo_mobileapp.utils.Constant

class emailRegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityEmailRegisterBinding
    private lateinit var mFirestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()

        binding.ivBackButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.cvRegisterButton.setOnClickListener {
            registerClick()
        }
        binding.loginAccess.setOnClickListener {
            val intent = Intent(this, emailLoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registerClick() {
        when {
            TextUtils.isEmpty(binding.tvFirstNameField.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan nama depan anda",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(binding.tvLastNameField.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan nama belakang anda",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(binding.tvEmailField.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan email anda",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(binding.tvPasswordField.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    this,
                    "Tolong masukkan password anda",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val firstName: String = binding.tvFirstNameField.text.toString().trim { it <= ' ' }
                val lastName: String = binding.tvLastNameField.text.toString().trim { it <= ' ' }
                val email: String = binding.tvEmailField.text.toString().trim { it <= ' ' }
                val password: String = binding.tvPasswordField.text.toString().trim { it <= ' ' }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val user = UserEmail(
                                firebaseUser.uid,
                                firstName,
                                lastName,
                                email
                            )
//                            UserEmailFirestore().registerUser(this, user)
                            saveDataUserToFirestore(user)
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }

    private fun saveDataUserToFirestore(user: UserEmail) {
        FirebaseFirestore.getInstance().collection(Constant.USERS)
            .document(user.id)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                Log.d("tes","success register")
                userRegistrationSuccess(user)
            }
    }

    fun userRegistrationSuccess(user: UserEmail) {
        Toast.makeText(this, "You are register Successfully", Toast.LENGTH_SHORT).show()
//        intent.putExtra(Constant.EXTRA_USER_DETAIL,user)
//        startActivity(Intent(this,EditProfileActivity::class.java))
        if (user.profileComplete == 0) {
//            val intent = Intent(this, MommyValidActivity::class.java)
//            intent.putExtra(Constant.EXTRA_USER_DETAIL,user)
//            startActivity(intent)


            val mommyValidFragment = MommyValidFragment()
            val mBundle = Bundle()
            mBundle.putString(Constant.EXTRA_USER_DETAIL, user.firstName)
            mommyValidFragment.arguments = mBundle
            supportFragmentManager.findFragmentById(R.id.container_register)
            setFragment(mommyValidFragment)


//            val mBundle = Bundle()
//            mBundle.putString(Constant.EXTRA_USER_DETAIL, user.toString())
//            val mFragmentTransaction = this.supportFragmentManager.beginTransaction()
//            val mFragment = MommyValidFragment()
//            mFragment.arguments = mBundle
//
//            mFragmentTransaction.replace(R.id.fragment_mommy_valid, mFragment)
//            mFragmentTransaction.commit()
//        } else {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//
        }
//        finish()
    }

    private fun setFragment(writeDescFragment: MommyValidFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, writeDescFragment)
        fragmentTransaction.commit()
    }


}