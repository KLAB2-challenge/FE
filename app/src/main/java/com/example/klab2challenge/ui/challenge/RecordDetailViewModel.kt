package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.GetProofPostsResponse

class RecordDetailViewModel : ViewModel() {
    private var rd = GetProofPostResponse(-1, "", "", "", "")
    private val _recordDetail = MutableLiveData<GetProofPostResponse>()

    val recordDetail : LiveData<GetProofPostResponse> get() = _recordDetail

    init {
        _recordDetail.value = rd
    }

    fun setRecordDetail(record : GetProofPostResponse) {
        rd = record
        _recordDetail.value = rd
    }
}