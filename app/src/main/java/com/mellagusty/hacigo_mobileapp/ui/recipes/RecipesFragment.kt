package com.mellagusty.hacigo_mobileapp.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.RecipesAdapter
import com.mellagusty.hacigo_mobileapp.adapter.SectionPagerAdapter
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity
import com.mellagusty.hacigo_mobileapp.databinding.FragmentRecipesBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
//    private lateinit var adapter: RecipesAdapter
    private lateinit var recipesViewModel: RecipesViewModel

    private var bahan: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun pagerAdapter(name: String){
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity() as AppCompatActivity, name)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        recipesViewModel = ViewModelProvider(this, factory).get(RecipesViewModel::class.java)
        pagerAdapter("name")

//        if (arguments?.getString("bahan") != null) {
//            bahan = arguments?.getString("bahan")
//
//            recipesViewModel.fetchRecipesByBahan(bahan!!).observe(viewLifecycleOwner) {
//                binding.rvRecipes.layoutManager =
//                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//                adapter = RecipesAdapter {
//                    val intent = Intent(requireContext(), RecipesDetailActivity::class.java)
//                    intent.putExtra(RecipesDetailActivity.EXTRA_JUDUL, it.judul)
//                    startActivity(intent)
//                }
//                adapter.setListData(it)
//                adapter.notifyDataSetChanged()
//                binding.arrowBack.setOnClickListener {
//                    val intent = Intent(requireContext(), MainActivity::class.java)
//                    startActivity(intent)
//                }
//                binding.rvRecipes.adapter = adapter
//            }
//        } else {
//            recipesViewModel.fetchRecipesData().observe(viewLifecycleOwner) {
//                binding.rvRecipes.layoutManager =
//                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//                adapter = RecipesAdapter {
//                    val intent = Intent(requireContext(), RecipesDetailActivity::class.java)
//                    intent.putExtra(RecipesDetailActivity.EXTRA_JUDUL, it.judul)
//                    Log.d("startActivity", "new Detail Activity")
//                    startActivity(intent)
//                }
//                adapter.setListData(it)
//                adapter.notifyDataSetChanged()
//                binding.arrowBack.setOnClickListener {
//                    val intent = Intent(requireContext(), MainActivity::class.java)
//                    startActivity(intent)
//                }
//                binding.rvRecipes.adapter = adapter
//
//                Log.d("this is", "ini data Anda untuk fragment$it")
//            }
//        }


        binding.arrowBack.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

    }
    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.bulan_semua,
            R.string.bulan_6_10,
            R.string.bulan_11_18,
            R.string.bulan_19_24

        )
    }


}