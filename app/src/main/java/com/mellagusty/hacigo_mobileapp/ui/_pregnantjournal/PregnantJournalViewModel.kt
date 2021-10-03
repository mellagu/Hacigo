package com.mellagusty.hacigo_mobileapp.ui._pregnantjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.mellagusty.hacigo_mobileapp.data.Repository
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.data.local.journal.PregnantJournalEntity
import com.mellagusty.hacigo_mobileapp.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class PregnantJournalViewModel(private val repository: Repository) : ViewModel() {

    companion object {
        private const val CHART_LABEL = "Berat Badan Terhadap Usia Kehamilan"

    }

    private val _lineDataSet = MutableLiveData<LineDataSet>()
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet

    suspend fun updateJournal(journalEntity: KiddoJournalEntity){
        return repository.updateJournal(journalEntity)
    }

    suspend fun getPregnantJournalAll(): List<PregnantJournalEntity> {
        return repository.getAllPregnantJournal()
    }

    fun getPregnantJournalData(){
        viewModelScope.launch {
            val list = repository.getAllPregnantJournal().sortedBy { it.ageInMonth }
            val listEntry = list.map {
                Entry(it.ageInMonth!!.toFloat(), it.weight!!.toFloat())
            }
            _lineDataSet.value = LineDataSet(listEntry, PregnantJournalViewModel.CHART_LABEL)
        }
    }

    suspend fun getLastPregnantJournal(): PregnantJournalEntity {
        return repository.getLastPregnantJournal()
    }

}