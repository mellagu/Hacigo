package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.SlideItemContainerBinding
import com.mellagusty.hacigo_mobileapp.intro.IntroSlideEntity

class IntroSliderAdapter(private val introSlide: List<IntroSlideEntity>): RecyclerView.Adapter<IntroSliderAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = SlideItemContainerBinding.bind(itemView)

        var textTitle = binding.tvTitle
        var textDesc = binding.tvDescription
        var imageIcon = binding.imgSlideIcon

        fun bind(introSlide: IntroSlideEntity){
            textTitle.text = introSlide.title
            textDesc.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }


}