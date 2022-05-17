package com.mellagusty.hacigo_mobileapp.ui.validation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.FragmentKiddoIdentityBinding
import com.mellagusty.hacigo_mobileapp.utils.Constant


class KiddoIdentityFragment : Fragment() {
    private var _binding: FragmentKiddoIdentityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKiddoIdentityBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gender = MutableLiveData<String>()
        binding.rbFemale.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            gender.value = "Female"

        }
        binding.rbMale.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            gender.value = "Male"
        }
        gender.observe(viewLifecycleOwner) {
            Log.d("gender", "your gender is ${gender.value}")
        }

        saveData()

    }

    private fun saveData() {
        binding.cvKiddoIdentityButton.setOnClickListener {
            val kidName = binding.tvNameKiddoField.text.toString().trim { it <= ' ' }
            val kidGender = if (binding.rbMale.isChecked) {
                Constant.MALE
            } else {
                Constant.FEMALE
            }

            val userHashMap = HashMap<String, Any>()
            userHashMap[Constant.KID_NAME] = kidName
            userHashMap[Constant.GENDER] = kidGender

            Log.d("tag", "check data name : $kidName and gender : $kidGender")

            val uid = FirebaseAuth.getInstance().uid
            val referenceUpdate = FirebaseFirestore.getInstance().collection(Constant.USERS)
                .document(uid!!)
            referenceUpdate.set(
                userHashMap,
                SetOptions.mergeFields(Constant.KID_NAME, Constant.GENDER)
            )
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e ->
                    Log.w(
                        "TAG",
                        "DocumentSnapshot Error updating document",
                        e
                    )
                }

            //move to the next fragment
            val supportFragment = activity?.supportFragmentManager
            supportFragment?.findFragmentById(R.id.container_register)
            val fragmentTransaction = supportFragment?.beginTransaction()
            fragmentTransaction?.replace(android.R.id.content, KiddoValidFragment())
            fragmentTransaction?.commit()
        }
    }
}