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
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()

    fun requestOfficialChallenges(userName: String) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            challengeRepository.requestOfficialChallenges(userInfo.name)
        }
    }

    fun requestUserChallenges(userName: String) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            challengeRepository.requestUserChallenges(userInfo.name)
        }
    }

    fun requestPopularChallenges(userName: String) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            challengeRepository.requestPopularChallenges(userInfo.name)
        }
    }
}