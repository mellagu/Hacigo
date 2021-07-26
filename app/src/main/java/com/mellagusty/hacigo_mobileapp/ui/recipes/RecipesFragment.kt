package com.mellagusty.hacigo_mobileapp.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.adapter.RecipesAdapter
import com.mellagusty.hacigo_mobileapp.databinding.FragmentRecipesBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var adapter: RecipesAdapter
    private lateinit var recipesViewModel: RecipesViewModel

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
        val factory = ViewModelFactory.getInstance(requireContext())
        recipesViewModel = ViewModelProvider(this, factory).get(RecipesViewModel::class.java)

        observeData()
        showRecycleCard()


    }

    private fun observeData() {
        recipesViewModel.fetchUserData().observe(viewLifecycleOwner, { recipe ->
            adapter.setListData(recipe)
            adapter.notifyDataSetChanged()
            Log.d("TAG","ini data untuk recycleview $recipe")

        })
    }


    private fun showRecycleCard() {
        binding.rvRecipes.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = RecipesAdapter {
            val intent = Intent(requireContext(), RecipesDetailActivity::class.java)
            startActivity(intent)
        }
        binding.rvRecipes.adapter = adapter
    }


}