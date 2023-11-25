package com.example.klab2challenge.ui.mychallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.GetChallengeResponse

class MyChallengeViewModel : ViewModel() {
    private var list = ArrayList<GetChallengeResponse>();
    private var _itemList = MutableLiveData<ArrayList<GetChallengeResponse>>();

    val itemList: LiveData<ArrayList<GetChallengeResponse>> get() = _itemList

    init {
        list = arrayListOf(
            GetChallengeResponse(
                0,
                ChallengeContents("title", "", ""),
                ChallengeInfos("", "", "", 0, false),
                0,
                true
            )
        )

        _itemList.value = list
    }

    fun setMyChallenge(cList: ArrayList<GetChallengeResponse>) {
        list = cList

        _itemList.value = list
    }
}