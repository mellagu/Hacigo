package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.knowledgebase.KnowledgebaseEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvKnowledgebaseBinding

class KnowledgebaseAdapter(private val listener: (KnowledgebaseEntity) -> Unit):
    RecyclerView.Adapter<KnowledgebaseAdapter.KbViewHolder>() {

    private var list: List<KnowledgebaseEntity> = ArrayList()

    fun setListData(kb: List<KnowledgebaseEntity>) {
        list = kb
        notifyDataSetChanged()
    }

    inner class KbViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRvKnowledgebaseBinding.bind(itemView)
        fun bind(position: Int) {
            val kb = list[position]
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }

            binding.tvTitleListkb.text = kb.title_kb
            binding.tvDescKb.text = kb.meta_kb

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KbViewHolder {
        return KbViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_knowledgebase, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KbViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}