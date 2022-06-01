package com.mellagusty.hacigo_mobileapp.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.googleauth.UserGmail
import com.mellagusty.hacigo_mobileapp.databinding.ActivityLoginBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidFragment
import com.mellagusty.hacigo_mobileapp.utils.Constant

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth

    //facebook
    private lateinit var callbackManager: CallbackManager

    //google
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 120
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mAuth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create() //for facebook
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()



        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.cvAuthGoogle.setOnClickListener {
            signInGoogle()
        }

        binding.cvAuthEmail.setOnClickListener {
            val intent = Intent(this, emailRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnFacebookLogin.setReadPermissions("email")

        binding.btnFacebookLogin.setOnClickListener {
            signInFacebook()
        }
    }

    private fun signInFacebook() {
        binding.btnFacebookLogin.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result!!.accessToken)
                }

                override fun onCancel() {
                    TODO("Not yet implemented")
                }

                override fun onError(error: FacebookException?) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        //get Credential
        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        mAuth.signInWithCredential(credential)
            .addOnFailureListener { e ->
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener { result ->
//                Log.d(TAG, "signInWithCredential:success")
//                val intent = Intent(this,MainActivity::class.java)
//                startActivity(intent)
//                finish()
                val user = mAuth.currentUser
//                FirebaseFirestore.getInstance().collection("users")
//                    .whereEqualTo()
            }

    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from FacebookSignInApi.getSignInIntent(...);
        callbackManager.onActivityResult(requestCode, resultCode, data)


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                }
            } else {
                Log.d("SignInActivity", exception.toString())
            }

        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val intent = Intent(this,MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    val user = mAuth.currentUser
//                    val userData = UserEmail()
                    FirebaseFirestore.getInstance().collection(Constant.USERS)
                        .whereEqualTo("id", user?.uid)
                        .get()
                        .addOnSuccessListener {
                            if (it.isEmpty) {
                                setDataToFireStore(
                                    user?.uid, user?.displayName, user?.email
//                                    user?.uid, user?.displayName,user?.email
                                )
                            } else {
//                                val intent = Intent(this,MainActivity::class.java)
//                                startActivity(intent)
                                val mommyValidFragment = MommyValidFragment()
                                supportFragmentManager.findFragmentById(R.id.container_register)
                                setFragment(mommyValidFragment)
                        }

                        }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun setFragment(mommyValidFragment: MommyValidFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, mommyValidFragment)
        fragmentTransaction.commit()
    }

    private fun setDataToFireStore(
        uid: String?,
        displayName: String?,
        email: String?
    ) {
        val user = UserGmail(
            id = uid,
            firstName = displayName,
            email = email

        )
        FirebaseFirestore.getInstance().collection(Constant.USERS)
            .document(uid.toString())
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(this, "user berhasil di save", Toast.LENGTH_SHORT).show()
            }

    }
}