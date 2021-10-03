package com.mellagusty.hacigo_mobileapp.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.databinding.ActivityEmailRegisterBinding
import com.mellagusty.hacigo_mobileapp.ui.validation.ValidationActivity
import java.util.*

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
        if (binding.tvNameField.text.toString().isEmpty()) {
            binding.tvNameField.error = "Tolong masukkan nama"
            binding.tvNameField.requestFocus()
            return
        }

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

        mAuth.createUserWithEmailAndPassword(
            binding.tvEmailField.text.toString(),
            binding.tvPasswordField.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userID = mAuth.currentUser?.uid
                                val documentReference =
                                    mFirestore.collection("users").document(userID!!)
                                val user: MutableMap<String, Any> = HashMap()
                                user["name"] = binding.tvNameField.text.toString()
                                user["email"] = binding.tvEmailField.text.toString()
                                user["password"] = binding.tvPasswordField.text.toString()
                                documentReference.set(user).addOnSuccessListener {
                                    Log.d(TAG, "Success on creating $userID")
                                    val intent = Intent(this, ValidationActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }.addOnFailureListener {
                                    Log.d(TAG, "failure on creating", task.exception)
                                    Toast.makeText(
                                        baseContext, "Pendaftaran gagal.\n Coba beberapa saat lagi",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Pendaftaran gagal.\n Coba beberapa saat lagi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }


}