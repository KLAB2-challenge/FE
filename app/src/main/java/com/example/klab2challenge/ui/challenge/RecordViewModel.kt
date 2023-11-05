package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetProofPostResponse

class RecordViewModel : ViewModel() {
    private var list = ArrayList<GetProofPostResponse>()
    private val _itemList = MutableLiveData<List<GetProofPostResponse>>()
    val itemList: LiveData<List<GetProofPostResponse>> get() = _itemList

    init {
        list = arrayListOf()
        _itemList.value = list
    }

    fun addRecords(challenge : GetProofPostResponse) {
        list.add(challenge)

        _itemList.value = list
    }

    fun addRecords(challenges : List<GetProofPostResponse>) {
        list.addAll(challenges)

        _itemList.value = list
    }
    fun deleteRecords(challenge : GetProofPostResponse) {
        list.remove(challenge)

        _itemList.value = list
    }
}