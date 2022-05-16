package com.mellagusty.hacigo_mobileapp.data.auth.emailauth

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mellagusty.hacigo_mobileapp.ui.account.EditProfileActivity
import com.mellagusty.hacigo_mobileapp.ui.auth.emailLoginActivity
import com.mellagusty.hacigo_mobileapp.ui.auth.emailRegisterActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidActivity
import com.mellagusty.hacigo_mobileapp.ui.validation.MommyValidFragment
import com.mellagusty.hacigo_mobileapp.utils.Constant

class UserEmailFirestore {
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

    //function to register an account
    fun registerUser(activity: emailRegisterActivity, userInfo: UserEmail) {
        mFirestore.collection(Constant.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess(userInfo)

            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    //function to retrieve userID
    fun getCurrentUserID(): String {
        val currentUser = mAuth.currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    //function to get user detail
    fun getUserDetails(activity: Activity) {
        mFirestore.collection(Constant.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                val user = document.toObject(UserEmail::class.java)!!

                val sharedPreferences = activity.getSharedPreferences(Constant.HACIGO_PREFERENCES,
                    Context.MODE_PRIVATE)
                val editor : SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Constant.LOGGED_IN_USERNAME,
                    user.firstName
                )
                editor.apply()


                when (activity) {
//                    is emailLoginActivity -> {
//                        activity.userLoggedInSuccess(user)
//                    }
                    is emailRegisterActivity -> {
                        activity.userRegistrationSuccess(user)
                    }

                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting user details",
                    e
                )
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        mFirestore.collection(Constant.USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is EditProfileActivity -> {
                        activity.userProfileUpdateSuccess()
                    }
                    is MommyValidActivity -> {
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the user details.",
                    e
                )

            }
    }
}