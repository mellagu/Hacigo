package com.mellagusty.hacigo_mobileapp.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.databinding.FragmentRecipesBinding

class recipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesViewModel: recipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveFirestoreGenerate()

    }


    private fun saveFirestoreGenerate() {
        val db = FirebaseFirestore.getInstance()


    }

}