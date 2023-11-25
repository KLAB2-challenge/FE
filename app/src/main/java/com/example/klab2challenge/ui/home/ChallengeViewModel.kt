package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.GetChallengeResponse

class ChallengeViewModel {
    private var list = ArrayList<GetChallengeResponse>()
    private val _itemList = MutableLiveData<List<GetChallengeResponse>>()
    val itemList: LiveData<List<GetChallengeResponse>> get() = _itemList


    init {
        list = arrayListOf(
//            GetChallengeResponse(
//                0,
//                ChallengeContents("abc", "abc", ""),
//                ChallengeInfos("startdate", "enddate", "freq", 0, false),
//                1,
//                true
//            ),
//            GetChallengeResponse(
//                2,
//                ChallengeContents("abc2", "abc2", ""),
//                ChallengeInfos("startdate2", "enddate2", "freq2", 0, false),
//                1,
//                true
//            )
        )

        _itemList.value = list
    }

    fun addChallenge(challenge: GetChallengeResponse) {
        list.add(challenge)

        _itemList.value = list
    }

    fun addChallenges(challenges: List<GetChallengeResponse>) {
        list.addAll(challenges)

        _itemList.value = list
    }

    fun deleteChallenge(challenge: GetChallengeResponse) {
        list.remove(challenge)

        _itemList.value = list
    }
}