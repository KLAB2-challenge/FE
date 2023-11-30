package com.example.klab2challenge.ui.mychallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class MyChallengeFragmentViewModel(
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val myChallenges = challengeRepository.myChallenges.asLiveData()

    fun requestOfficialChallenges(userName: String) {
        viewModelScope.launch {
            challengeRepository.requestOfficialChallenges(userName)
        }
    }

    fun requestUserChallenges(userName: String) {
        viewModelScope.launch {
            challengeRepository.requestUserChallenges(userName)
        }
    }

    fun requestPopularChallenges(userName: String) {
        viewModelScope.launch {
            challengeRepository.requestPopularChallenges(userName)
        }
    }
}