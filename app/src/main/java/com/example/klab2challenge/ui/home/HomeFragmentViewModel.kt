package com.example.klab2challenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel(
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()

    fun requestMyChallenges(userName: String) {
        viewModelScope.launch {
            challengeRepository.requestMyChallenges(userName)
        }
    }
}