package com.example.klab2challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.ChallengeEntity
import com.example.klab2challenge.db.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()
    val myChallenges = challengeRepository.myChallenges.asLiveData()

    fun insert(challenge : ChallengeEntity) {
        viewModelScope.launch {
            challengeRepository.insert(challenge)
        }
    }

    fun requestOfficialChallenge(userName : String) {
        viewModelScope.launch {
            challengeRepository.requestOfficialChallenges(userName)
        }
    }
    fun requestUserChallenge(userName : String) {
        viewModelScope.launch {
            challengeRepository.requestUserChallenges(userName)
        }
    }

    fun requestPopularChallenge(userName : String) {
        viewModelScope.launch {
            challengeRepository.requestPopularChallenges(userName)
        }
    }

    fun requestMyChallenge(userName : String) {
        viewModelScope.launch {
            challengeRepository.requestMyChallenges(userName)
        }
    }
}