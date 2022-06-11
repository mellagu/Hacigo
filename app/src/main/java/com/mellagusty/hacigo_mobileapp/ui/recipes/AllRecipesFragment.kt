package com.mellagusty.hacigo_mobileapp.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mellagusty.hacigo_mobileapp.adapter.RecipesAdapter
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity
import com.mellagusty.hacigo_mobileapp.databinding.FragmentAllRecipesBinding
import com.mellagusty.hacigo_mobileapp.utils.Constant
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory


class AllRecipesFragment : Fragment() {

    private lateinit var binding: FragmentAllRecipesBinding
    private lateinit var adapter: RecipesAdapter
    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(RecipesViewModel::class.java)

        viewModel.fetchRecipesData().observe(viewLifecycleOwner) {
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("tag","Nilai query: $query")
//                if (query != null) {
                    val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
                val listData = ArrayList<RecipesEntity>()
                    FirebaseFirestore.getInstance().collection(Constant.COOK)
                        .whereEqualTo("judul", query).get()
                        .addOnSuccessListener { result ->
                            Log.d("tag","Nilai result: ${result.documents.get(0).data}")

            //                            for (document in result) {
                            val recipes = result.documents.get(0).data
                            val newRecipe = RecipesEntity(
                                judul = recipes?.get("judul").toString(),
                                subJudul = recipes?.get("subJudul").toString(),
                                usia = recipes?.get("usia").toString().toInt()
                            )
                            Log.d("this", "Firestore, ini data Anda $newRecipe")
                            listData.add(newRecipe)
                            binding.rvRecipes.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                            adapter = RecipesAdapter {
                                val intent = Intent(requireContext(), RecipesDetailActivity::class.java)
                                intent.putExtra(RecipesDetailActivity.EXTRA_JUDUL, it.judul)
                                Log.d("startActivity", "new Detail Activity")
                                startActivity(intent)
                            }
                            adapter.setListData(listData)
                            adapter.notifyDataSetChanged()
                            binding.rvRecipes.adapter = adapter
            //                            }
                            mutableData.postValue(listData)
                        }
//                    adapter.notifyDataSetChanged()


//                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val mutableData = MutableLiveData<MutableList<RecipesEntity>>()
                    FirebaseFirestore.getInstance().collection(Constant.COOK)
                        .whereArrayContains("judul", newText).get()
                        .addOnSuccessListener { result ->
                            val listData = mutableListOf<RecipesEntity>()
                            for (document in result) {
                                val recipes = document.toObject(RecipesEntity::class.java)
                                Log.d("this", "Firestore, ini data Anda $recipes")
                                listData.add(recipes)
                            }
                            mutableData.postValue(listData)
                        }
//                    adapter.notifyDataSetChanged()
                    binding.rvRecipes.adapter = adapter

                }
                return true
            }
        })

    }


    companion object {
        const val ALL_RESEP = "ALL_RESEP"

        @JvmStatic
        fun newInstance(name: String) =
            AllRecipesFragment().apply {
                arguments = Bundle().apply {
                    putString(ALL_RESEP, name)
                }
            }
    }
}