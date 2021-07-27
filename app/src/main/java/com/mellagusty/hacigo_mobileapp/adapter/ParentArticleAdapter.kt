package com.mellagusty.hacigo_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.article.ArticleEntity
import com.mellagusty.hacigo_mobileapp.databinding.ItemRvArticleBinding

class ParentArticleAdapter(private val listener: (ArticleEntity) -> Unit) :
    RecyclerView.Adapter<ParentArticleAdapter.ArticleViewHolder>() {

    private var list: List<ArticleEntity> = ArrayList()

    fun setListData(article: List<ArticleEntity>) {
        list = article
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val article = list[position]
            itemView.setOnClickListener {
                listener(list[absoluteAdapterPosition])
            }
            val binding = ItemRvArticleBinding.bind(itemView)
            val title = binding.tvTitleArticle
            val writer = binding.tvWriter
            val desc = binding.descArticle
            val image = binding.ivArticle
            Glide.with(itemView.context)
                .load(article.image)
                .into(image)
            title.text = article.title
            writer.text = article.writer
            desc.text = article.desc

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}