package com.mellagusty.hacigo_mobileapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    companion object {
        private const val CHART_LABEL = "Berat Badan Terhadap Usia"

    }

    private val _lineDataSet = MutableLiveData<LineDataSet>()
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet


    fun getJournalData(){
        viewModelScope.launch {
            val list = repository.getJournalAll().sortedBy { it.ageInMonth }
            Log.d("TAG","listJournal : $list")
            val listEntry = list.map {
                Log.d("TAG","it.ageInMonth!!.toFloat() = ${it.ageInMonth!!.toFloat()} . it.weight!!.toFloat() = ${it.weight!!.toFloat()} ")
                Entry(it.ageInMonth!!.toFloat(), it.weight!!.toFloat())
            }
             _lineDataSet.value = LineDataSet(listEntry, CHART_LABEL)

        }


    }




}