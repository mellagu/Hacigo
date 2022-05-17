package com.mellagusty.hacigo_mobileapp.ui.validation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmailFirestore
import com.mellagusty.hacigo_mobileapp.databinding.FragmentMommyValidBinding
import com.mellagusty.hacigo_mobileapp.utils.Constant


class MommyValidFragment : Fragment() {

    private lateinit var _binding: FragmentMommyValidBinding
    private val binding get() = _binding
    var isMommyValidFragment = true
    lateinit var userDetail: UserEmail
    private val manager = activity?.supportFragmentManager?.beginTransaction()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMommyValidBinding.inflate(inflater, container, false)
        return _binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        val firstName = bundle?.getString(Constant.EXTRA_USER_DETAIL, " ")
        userDetail = UserEmail()
        saveData()

        //view mommy's name
        val sharedPreferences =
            activity?.getSharedPreferences(Constant.HACIGO_PREFERENCES, Context.MODE_PRIVATE)
//        val firstName = sharedPreferences?.getString(Constant.LOGGED_IN_USERNAME, " ")!!
        binding.tvMomName.text = "$firstName"

        super.onViewCreated(view, savedInstanceState)
    }

    private fun saveData() {
        binding.cvMomValidSubmit.setOnClickListener {
//            Log.d("tag","check data $userHashMap")
            //NON DEFAULT DATA THAT WILL BE SAVE IN FIRESTORE
            val ageMom = binding.tvAgeMomField.text.toString().trim { it <= ' ' }
//            if (ageMom.isEmpty()){
//                userHashMap[Constant.AGE] = ageMom.toInt()
//            }
            val location = binding.tvLocationField.text.toString().trim { it <= ' ' }
//            if (location.isEmpty()){
//                userHashMap[Constant.LOCATION] = location
//            }

            val userHashMap = hashMapOf(
                Constant.AGE to binding.tvAgeMomField.text.toString().trim(),
                Constant.LOCATION to binding.tvLocationField.text.toString().trim()
            )

            Log.d("tag", "check data age and location $ageMom $location")
            val uid = FirebaseAuth.getInstance().uid

            val referenceUpdate = FirebaseFirestore.getInstance().collection(Constant.USERS)
                .document(uid!!)
            referenceUpdate.set(userHashMap, SetOptions.mergeFields("age","location"))
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e ->
                    Log.d(
                        "TAG",
                        "DocumentSnapshot Error updating document ${e.localizedMessage}"
                    )
                }


//            FirebaseFirestore.getInstance().collection(Constant.USERS)
//                .document(uid!!)
//                .update(userHashMap, SetOptions.merge())
//                .addOnSuccessListener {
//                    Log.d("TAG", "DocumentSnapshot successfully written!")
//                }
//                .addOnFailureListener { e ->
//                    Log.d("TAG", "Error writing document ${e.localizedMessage}" )
//            }


//            UserEmailFirestore().updateUserProfileData(requireActivity(), userHashMap)

//            //move to the next activity
//            manager?.replace(R.id.container_register, PregOrNoValidFragment())
//            manager?.addToBackStack(null)
//            manager?.commit()

//TODO update data user
        }
    }


    companion object {
    }
}