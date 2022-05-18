package com.mellagusty.hacigo_mobileapp.ui.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.databinding.FragmentAccountBinding
import com.mellagusty.hacigo_mobileapp.databinding.FragmentRecipesBinding
import com.mellagusty.hacigo_mobileapp.ui.auth.LoginActivity
import com.mellagusty.hacigo_mobileapp.ui.dummy_develop.DummyDevelopActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant

class AccountFragment : Fragment() {

//    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    lateinit var userDetail: UserEmail

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        val currentUser = mAuth.currentUser
        binding.tvEmailField.text = currentUser?.email

        mFirestore.collection(Constant.USERS)
            .get()
            .addOnSuccessListener {
                binding.tvNameField.text = "${it.documents.get(0).data?.get(Constant.FIRST_NAME)} ${it.documents.get(0).data?.get(Constant.LAST_NAME)}"
                binding.tvAgeField.text = "${it.documents.get(0).data?.get(Constant.AGE)}"
                binding.tvKidField.text = "${it.documents.get(0).data?.get(Constant.KID_NAME)}"
                binding.tvGenderKidField.text = "${it.documents.get(0).data?.get(Constant.GENDER)}"
                binding.tvLocationField.text = "${it.documents.get(0).data?.get(Constant.LOCATION)}"


            }
            .addOnFailureListener {
                Log.d("tag","Found the error ")
            }





//        Glide.with(this).load(currentUser?.photoUrl).into(binding.ivMom)

        binding.btnSignout.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


//        //get name from Email account
//        val documentReference = mFirestore.collection("users").document(userId!!)
//        documentReference.addSnapshotListener{ snapshot, e ->
//
//
//        }


        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(requireContext(),EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

}