package com.mellagusty.hacigo_mobileapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.journal.AllJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvJournalBinding
import java.io.Serializable

//class KiddoJournalAdapter(private val listener: (KiddoJournalEntity) -> Unit) :
class KiddoJournalAdapter(private val listener: (AllJournalEntity) -> Unit) :
    RecyclerView.Adapter<KiddoJournalAdapter.JournalViewHolder>() {


//    private var list: List<KiddoJournalEntity> = ArrayList()
    private var list: List<AllJournalEntity> = ArrayList()

    fun setListData(kiddo: List<AllJournalEntity>) {
        list = kiddo
        notifyDataSetChanged() //the adapter will reflect the change
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val kiddo = list[position] as AllJournalEntity
            itemView.setOnClickListener {
//                listener(list[absoluteAdapterPosition])
                listener(list[absoluteAdapterPosition])
            }
            val binding = ItemRvJournalBinding.bind(itemView)
            val tvTitle = binding.tvTitle
            val tvDesc = binding.tvDesc
            val tvDate = binding.tvDateTime

            tvTitle.text = kiddo.title
            tvDesc.text = kiddo.journalText
            tvDate.text = kiddo.dateTime

//            tvTitle.text = kiddo.title
//            tvDesc.text = kiddo.journalText
//            tvDate.text = kiddo.dateTime


            if (list[position].color != null) {
                binding.cardView.setCardBackgroundColor(Color.parseColor(kiddo.color))
            } else {
                binding.cardView.setCardBackgroundColor(Color.parseColor(R.color.pink.toString()))
            }
            if (list[position].imgPath != null) {
                binding.imgNote.setImageBitmap(BitmapFactory.decodeFile(kiddo.imgPath))
                binding.imgNote.visibility = View.VISIBLE
            } else {
                binding.imgNote.visibility = View.GONE
            }

            if (list[position].webLink != "") {
                binding.tvWebLink.text = kiddo.webLink
                binding.tvWebLink.visibility = View.VISIBLE
            } else {
                binding.tvWebLink.visibility = View.GONE
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        return JournalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_journal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}