package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.event.EventEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvEventBinding

class ParentEventAdapter(private val listener:(EventEntity)-> Unit): RecyclerView.Adapter<ParentEventAdapter.EventViewHolder>() {

    private var list: List<EventEntity> = ArrayList()

    fun setListData(eventEntity: List<EventEntity>){
        list = eventEntity
        notifyDataSetChanged()
    }

    inner class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int){
            val event = list[position]
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }
            val binding = ItemRvEventBinding.bind(itemView)
            val titleEvent = binding.tvTitleEvent
            val noteSpeaker = binding.tvNoteSpeaker
            val schedule = binding.tvSchedule
            val heldOn = binding.tvHeld
            val image = binding.ivEvent

            Glide.with(itemView.context)
                .load(event.image)
                .into(image)
            titleEvent.text = event.title_event
            noteSpeaker.text = event.noteSpeaker
            schedule.text = event.schedule
            heldOn.text = event.heldOn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_event,parent,false)
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}