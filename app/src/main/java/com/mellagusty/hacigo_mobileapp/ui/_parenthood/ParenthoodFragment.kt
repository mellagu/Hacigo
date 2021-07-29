package com.mellagusty.hacigo_mobileapp.ui._parenthood

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.ParentArticleAdapter
import com.mellagusty.hacigo_mobileapp.adapter.ParentEventAdapter
import com.mellagusty.hacigo_mobileapp.data.local.article.ArticleEntity
import com.mellagusty.hacigo_mobileapp.data.local.event.EventEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.FragmentParenthoodBinding
import java.util.*
import kotlin.collections.ArrayList


class ParenthoodFragment : Fragment() {

    private var articles = mutableListOf<ArticleEntity>()
    private var event = mutableListOf<EventEntity>()
    private lateinit var binding : FragmentParenthoodBinding
    private lateinit var adapterArticle: ParentArticleAdapter
    private lateinit var adapterEvent: ParentEventAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentParenthoodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated called")

        showRecycleViewEvent()
        initDummyDataEvent()

        showRecycleViewArticle()
        initDummyDataArticle()

        searchBarArticle()

    }

    private fun searchBarArticle() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tempArray = ArrayList<ArticleEntity>()
                for (array in articles ){
                    if (array.title!!.toLowerCase(Locale.getDefault()).contains(newText.toString())){
                        tempArray.add(array)
                    }
                }
                adapterArticle.setListData(tempArray)
                adapterArticle.notifyDataSetChanged()
                return true
            }
        })
    }

    private fun initDummyDataEvent() {
        event.clear()
        event = DummyEvent()
        adapterEvent.setListData(event)
    }

    private fun showRecycleViewEvent() {
        binding.rvEvent.layoutManager =
            LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        adapterEvent = ParentEventAdapter {  }
        binding.rvEvent.adapter = adapterEvent
    }

    private fun showRecycleViewArticle() {
        binding.rvArticle.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        adapterArticle = ParentArticleAdapter {
            val intent = Intent(requireContext(),ArticleActivity::class.java)
            intent.putExtra(ArticleActivity.EXTRA_TITLE_ARTICLE,it.title)
            intent.putExtra(ArticleActivity.EXTRA_WRITER_ARTICLE,it.writer)
            intent.putExtra(ArticleActivity.EXTRA_DESC_ARTICLE,it.desc)
            intent.putExtra(ArticleActivity.EXTRA_IMAGE_ARTICLE,it.image)
            startActivity(intent)
        }
        binding.rvArticle.adapter = adapterArticle

    }
    private fun initDummyDataArticle() {
        articles.clear()
        articles = DummyArticle()
        adapterArticle.setListData(articles)


    }



}