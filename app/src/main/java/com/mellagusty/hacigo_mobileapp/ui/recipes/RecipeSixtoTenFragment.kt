package com.mellagusty.hacigo_mobileapp.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.RecipesAdapter
import com.mellagusty.hacigo_mobileapp.databinding.FragmentRecipeSixtoTenBinding
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory


class RecipeSixtoTenFragment : Fragment() {

    private lateinit var binding : FragmentRecipeSixtoTenBinding
    private lateinit var adapter: RecipesAdapter
    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeSixtoTenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(RecipesViewModel::class.java)

        viewModel.fetchRecipeSixToTen().observe(viewLifecycleOwner){
            binding.rvRecipes.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = RecipesAdapter {
                val intent = Intent(requireContext(), RecipesDetailActivity::class.java)
                intent.putExtra(RecipesDetailActivity.EXTRA_JUDUL, it.judul)
                Log.d("startActivity", "new Detail Activity")
                startActivity(intent)
            }
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
            binding.rvRecipes.adapter = adapter

            Log.d("this is", "ini data Anda untuk fragment$it")
        }

    }

    companion object {
        const val RESEP_6_10_BULAN = "RESEP_6_10_BULAN"

        @JvmStatic
        fun newInstance(name: String) =
            RecipeSixtoTenFragment().apply {
                arguments = Bundle().apply {
                    putString(RESEP_6_10_BULAN, name)
                }
            }
    }
}