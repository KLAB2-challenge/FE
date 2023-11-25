package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.GetChallengeResponse

class ChallengeDetailViewModel : ViewModel() {
    private var cd =
        GetChallengeResponse(
        -1,
        ChallengeContents("", "", ""),
        ChallengeInfos("", "", "", -1, false),
        -1,
        false,
        0.0
    )
    private val _challengeDetail = MutableLiveData<GetChallengeResponse>()

    val challengeDetail: LiveData<GetChallengeResponse> get() = _challengeDetail

    init {
        _challengeDetail.value = cd
    }

    fun setChallengeDetail(challengeDetail : GetChallengeResponse) {
        cd = challengeDetail
        _challengeDetail.value = cd
    }

    fun setChallengeDetailJoin(isjoin : Boolean) {
        cd = cd.copy(join = isjoin)
        _challengeDetail.value = cd
    }

}