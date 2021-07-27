package com.mellagusty.hacigo_mobileapp.ui._parenthood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.databinding.ActivityArticleBinding
import com.mellagusty.hacigo_mobileapp.ui._parenthood.util.EXTRA_DESC_ARTICLE
import com.mellagusty.hacigo_mobileapp.ui._parenthood.util.EXTRA_IMAGE_ARTICLE
import com.mellagusty.hacigo_mobileapp.ui._parenthood.util.EXTRA_TITLE_ARTICLE
import com.mellagusty.hacigo_mobileapp.ui._parenthood.util.EXTRA_WRITER_ARTICLE

class ArticleActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TITLE_ARTICLE = "extra_title_article"
        const val EXTRA_WRITER_ARTICLE = "extra_writer_article"
        const val EXTRA_DESC_ARTICLE = "extra_desc_article"
        const val EXTRA_IMAGE_ARTICLE = "extra_image_article"
    }

    private lateinit var binding : ActivityArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE_ARTICLE)
        val writer = intent.getStringExtra(EXTRA_WRITER_ARTICLE)
        val desc = intent.getStringExtra(EXTRA_DESC_ARTICLE)
        val image = intent.getIntExtra(EXTRA_IMAGE_ARTICLE,-1)

        Glide.with(this)
            .load(image)
            .into(binding.ivArticle)
        binding.tvTitleArticle.text = title
        binding.tvWriter.text = writer
        binding.tvDescParenthood.text = desc

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

    }
}