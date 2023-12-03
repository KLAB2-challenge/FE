package com.example.klab2challenge

import android.content.Context
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
    private var userName = ""

    fun setUserName(name: String) {
        userName = name
    }

    fun requestRanking() {
        viewModelScope.launch {
            rankingRepository.requestRanking(userName)
        }
    }

    fun requestUser() {
        viewModelScope.launch {
            userRepository.requestUser(userName)
        }
    }

    fun requestBorder(context : Context) {
        viewModelScope.launch {
            borderRepository.requestBorder(userName, context)
        }
    }

    fun requestChallengs() {
        viewModelScope.launch {
            challengeRepository.requestChallenges(userName)
        }
    }

}