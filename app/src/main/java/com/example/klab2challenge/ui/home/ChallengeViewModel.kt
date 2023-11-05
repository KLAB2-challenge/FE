package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.klab2challenge.retrofit.GetChallengeResponse

class ChallengeViewModel {
    private var list = ArrayList<GetChallengeResponse>()
    private val _itemList = MutableLiveData<List<GetChallengeResponse>>()
    val itemList: LiveData<List<GetChallengeResponse>> get() = _itemList


    init {
        list = arrayListOf()
        _itemList.value = list
    }
    fun addChallenge(challenge : GetChallengeResponse) {
        list.add(challenge)

        _itemList.value = list
    }

    fun addChallenges(challenges : List<GetChallengeResponse>) {
        list.addAll(challenges)

        _itemList.value = list
    }
    fun deleteChallenge(challenge : GetChallengeResponse) {
        list.remove(challenge)

        _itemList.value = list
    }
}