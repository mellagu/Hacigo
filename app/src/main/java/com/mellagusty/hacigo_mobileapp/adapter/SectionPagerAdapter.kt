package com.mellagusty.hacigo_mobileapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mellagusty.hacigo_mobileapp.ui.recipes.AllRecipesFragment
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipeEleventoEighteenFragment
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipeNinetintoTwenfFragment
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipeSixtoTenFragment

class SectionPagerAdapter(activity: AppCompatActivity, val name: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AllRecipesFragment()
            1 -> fragment = RecipeSixtoTenFragment()
            2 -> fragment = RecipeEleventoEighteenFragment()
            3 -> fragment = RecipeNinetintoTwenfFragment()
        }
        return (fragment as Fragment)
            .apply {
                arguments = Bundle().apply {
                    putString(AllRecipesFragment.ALL_RESEP, name)
                    putString(RecipeSixtoTenFragment.RESEP_6_10_BULAN, name)
                    putString(RecipeEleventoEighteenFragment.RESEP_11_18_BULAN, name)
                    putString(RecipeNinetintoTwenfFragment.RESEP_19_24_BULAN, name)
                }
            }
            }

    }