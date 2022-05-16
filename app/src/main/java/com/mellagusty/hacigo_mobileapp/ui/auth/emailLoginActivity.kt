package com.mellagusty.hacigo_mobileapp.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmailFirestore
import com.mellagusty.hacigo_mobileapp.databinding.ActivityEmailLoginBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui.dummy_develop.VerificationPursueActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidFragment
import com.mellagusty.hacigo_mobileapp.utils.Constant

class emailLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailLoginBinding
    private lateinit var mAuth: FirebaseAuth
//    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvLoginButton.setOnClickListener {
            signIn()
        }

        mAuth = FirebaseAuth.getInstance()

    }

    private fun signIn() {
        if (binding.tvEmailField.text.toString().isEmpty()) {
            binding.tvEmailField.error = "Tolong masukkan email"
            binding.tvEmailField.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.tvEmailField.text.toString()).matches()) {
            binding.tvEmailField.error = "Email anda tidak valid"
            binding.tvEmailField.requestFocus()
            return
        }
        if (binding.tvPasswordField.text.toString().isEmpty()) {
            binding.tvPasswordField.error = "Tolong masukkan password"
            binding.tvPasswordField.requestFocus()
            return

        }

        if (binding.tvPasswordField.text.toString().length < 6) {
            binding.tvPasswordField.error = "Password minimal 6 karakter"
            binding.tvPasswordField.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(
            binding.tvEmailField.text.toString(),
            binding.tvPasswordField.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
//                    val user = mAuth.currentUser
//                    updateUI(user)
                    UserEmailFirestore().getUserDetails(this)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Login gagal. Silahkan coba beberapa saat lagi",
                        Toast.LENGTH_SHORT
                    ).show()
//                    w
                }
            }
    }

//    public override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth.currentUser
//        updateUI(currentUser)
//    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, VerificationPursueActivity::class.java)
                startActivity(intent)
            }


        } else {
            Toast.makeText(
                baseContext, "Login gagal. Silahkan coba beberapa saat lagi",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun userLoggedInSuccess(user: UserEmail) {
        //Print the user details in the log
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        //redirect to the User Profile Activity
        // If the user profile is incomplete then launch the UserProfileActivity.
        if (user.profileComplete == 0) {
//            val intent = Intent(this, EditProfileActivity::class.java)
//            //put the extra information from this activity to next activity by an intent
//            intent.putExtra(Constant.EXTRA_USER_DETAIL,user)
//            startActivity(intent)
            val mBundle = Bundle()
            mBundle.putString(Constant.EXTRA_USER_DETAIL, user.toString())
            val mFragmentTransaction = this.supportFragmentManager.beginTransaction()
            val mFragment = MommyValidFragment()
            mFragment.arguments = mBundle

            mFragmentTransaction.replace(R.id.fragment_mommy_valid, mFragment)
            mFragmentTransaction.commit()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        finish()
    }
}