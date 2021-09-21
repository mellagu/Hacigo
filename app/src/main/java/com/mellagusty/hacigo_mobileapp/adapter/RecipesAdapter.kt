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
        fun bind(position: Int){
            val recipe = list[position]
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }
            val binding = ItemRvRecipeBinding.bind(itemView)
            val tvJudul = binding.titleRecipe
            val tvSubJudul = binding.descRecipe

            tvJudul.text = recipe.judul
            tvSubJudul.text = recipe.subJudul
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
       holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}