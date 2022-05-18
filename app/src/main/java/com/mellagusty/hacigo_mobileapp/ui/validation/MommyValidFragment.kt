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
        binding.tvMomName.text = "$firstName"

        userDetail = UserEmail()
        saveData()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun saveData() {
        binding.cvMomValidSubmit.setOnClickListener {

            //NON DEFAULT DATA THAT WILL BE SAVE IN FIRESTORE
            val ageMom = binding.tvAgeMomField.text.toString().trim { it <= ' ' }
            val location = binding.tvLocationField.text.toString().trim { it <= ' ' }

            val userHashMap = HashMap<String, Any>()
            userHashMap[Constant.AGE] = ageMom.toInt()
            userHashMap[Constant.LOCATION] = location

//            val userHashMap = hashMapOf(
//                Constant.AGE to binding.tvAgeMomField.text.toString().trim(),
//                Constant.LOCATION to binding.tvLocationField.text.toString().trim()
//            )

            Log.d("tag", "check data age and location $ageMom $location")
            val uid = FirebaseAuth.getInstance().uid

            val referenceUpdate = FirebaseFirestore.getInstance().collection(Constant.USERS)
                .document(uid!!)
            referenceUpdate.set(userHashMap, SetOptions.mergeFields(Constant.AGE,Constant.LOCATION))
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e ->
                    Log.w(
                        "TAG",
                        "DocumentSnapshot Error updating document",
                        e
                    )
                }

            //move to the next fragment
            val pregOrNoValidFragment = PregOrNoValidFragment()
            val mBundle = Bundle()
            mBundle.putString(Constant.LOCATION, userDetail.location)
            pregOrNoValidFragment.arguments = mBundle
            activity?.supportFragmentManager?.findFragmentById(R.id.container_register)
            setFragment(pregOrNoValidFragment)

        }
    }

    private fun setFragment(pregOrNoValidFragment: PregOrNoValidFragment) {
        val supportFragment = activity?.supportFragmentManager
        val fragmentTransaction = supportFragment?.beginTransaction()
        fragmentTransaction?.replace(android.R.id.content, pregOrNoValidFragment)
        fragmentTransaction?.commit()
    }

}