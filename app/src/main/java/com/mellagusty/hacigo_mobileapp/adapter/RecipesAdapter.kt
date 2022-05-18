package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvRecipeBinding

class RecipesAdapter(private val listener: (RecipesEntity) -> Unit) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var list: List<RecipesEntity> = ArrayList()

    fun setListData(recipe: List<RecipesEntity>) {
        list = recipe
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRvRecipeBinding.bind(itemView)
        fun bind(recipeList: RecipesEntity){

            binding.titleRecipe.text = recipeList.judul
            binding.descRecipe.text = recipeList.subJudul
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesAdapter.RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipesAdapter.RecipeViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}