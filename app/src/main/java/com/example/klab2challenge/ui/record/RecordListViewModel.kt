package com.example.klab2challenge.ui.record

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordListViewModel(val recordRepository: RecordRepository) : ViewModel() {
    val records = recordRepository.records.asLiveData()

    fun requestRecords(challengeId : Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                recordRepository.requestRecords(challengeId)
            }
        }
    }
}