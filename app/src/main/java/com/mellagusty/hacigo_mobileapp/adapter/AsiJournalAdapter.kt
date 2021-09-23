package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.journal.AsiJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvAsiBinding

class AsiJournalAdapter(private val listener: (AsiJournalEntity) -> Unit): RecyclerView.Adapter<AsiJournalAdapter.JournalViewHolder>(){

    private var list: MutableList<AsiJournalEntity> = ArrayList()

    fun setListData(asi: MutableList<AsiJournalEntity>) {
        list = asi
        notifyDataSetChanged()
    }

    inner class JournalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val asi = list[position]

            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }

            val binding = ItemRvAsiBinding.bind(itemView)

            binding.titleAsi.text = "Bulan ke-"+asi.bulanke

            if ( asi.asi == "ya" ) {
                binding.ivCheck.setImageResource(R.drawable.ic_asi_yes)
            }
            else if ( asi.asi == "tidak" ) {
                binding.ivCheck.setImageResource(R.drawable.ic_asi_no)
            }
            else {
                // belum
                binding.ivCheck.setImageResource(android.R.color.transparent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        return JournalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_asi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }



}