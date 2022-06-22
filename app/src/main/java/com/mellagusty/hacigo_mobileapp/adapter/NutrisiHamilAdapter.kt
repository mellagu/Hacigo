package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.nutrisi_ibu.NutrisiIbuEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvNutrisiBinding

class NutrisiHamilAdapter(private val listener: (NutrisiIbuEntity) -> Unit) :
    RecyclerView.Adapter<NutrisiHamilAdapter.NutrisiViewHolder>() {

    private var list: List<NutrisiIbuEntity> = ArrayList()
    fun setListData(nutrisi: List<NutrisiIbuEntity>) {
        list = nutrisi
        notifyDataSetChanged()
    }

    inner class NutrisiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRvNutrisiBinding.bind(itemView)
        fun bind(nutrisiList: NutrisiIbuEntity) {
            binding.titleNutrisi.text = nutrisiList.judul
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrisiViewHolder {
        return NutrisiViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_nutrisi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NutrisiViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}