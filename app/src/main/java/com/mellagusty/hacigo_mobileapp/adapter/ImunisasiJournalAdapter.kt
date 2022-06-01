package com.mellagusty.hacigo_mobileapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.imunisasi_journal.ImunisasiEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvImunisasiBinding
import com.mellagusty.hacigo_mobileapp.utils.Constant

class ImunisasiJournalAdapter(private val listener: (ImunisasiEntity) -> Unit) :
    RecyclerView.Adapter<ImunisasiJournalAdapter.JournalViewHolder>() {

    private var list: MutableList<ImunisasiEntity> = ArrayList()

    fun setListData(imunisasiList: MutableList<ImunisasiEntity>) {
        list = imunisasiList
        notifyDataSetChanged()
    }
//    var statusImunisasiDone: String = ""
//
//    fun setStatusImunisasi(statusImunisasi: String){
//        statusImunisasiDone = statusImunisasi
//    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            val imunisasi = list[position]

            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }

            val binding = ItemRvImunisasiBinding.bind(itemView)

            binding.titleImunisasi.text = "Imunisasi Bulan ke-${imunisasi.bulan}"
            binding.titleJenisImunisasi.text = convertToStringLines(imunisasi.jenisImunisasi)

            if (imunisasi.status_imunisasi == Constant.YA) {
                binding.ivCheck.setImageResource(R.drawable.ic_asi_yes)
            } else if (imunisasi.status_imunisasi == Constant.TIDAK) {
                binding.ivCheck.setImageResource(R.drawable.ic_asi_no)
            } else {
                // belum
                binding.ivCheck.setImageResource(android.R.color.transparent)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImunisasiJournalAdapter.JournalViewHolder {
        return JournalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_imunisasi, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ImunisasiJournalAdapter.JournalViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun convertToStringLines(arrstr: ArrayList<String>?): String {
        var newStr = ""
        arrstr?.forEach{
            newStr = newStr + it
            newStr = newStr + "\n"
        }
        Log.d("cek", newStr)
        return newStr
    }
}