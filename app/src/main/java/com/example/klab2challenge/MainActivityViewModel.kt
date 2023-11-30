package com.example.klab2challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val challengeRepository: ChallengeRepository,
    private val userRepository: UserRepository,
    private val rankingRepository: RankingRepository,
    private val borderRepository: BorderRepository
) : ViewModel() {
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()
    val myChallenges = challengeRepository.myChallenges.asLiveData()
    val borders = borderRepository.borders.asLiveData()
    val users = userRepository.users.asLiveData()
    val rankings = rankingRepository.rankings.asLiveData()

    fun requestRanking(userName: String) {
        viewModelScope.launch {
            rankingRepository.requestRanking(userName)
        }
    }

    fun requestUser(userName: String) {
        viewModelScope.launch {
            userRepository.requestUser(userName)
        }
    }

    fun requestBorder(userName: String) {
        viewModelScope.launch {
            borderRepository.requestBorder(userName)
        }
    }

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

    fun requestMyChallenges(userName: String) {
        viewModelScope.launch {
            challengeRepository.requestMyChallenges(userName)
        }
    }

}