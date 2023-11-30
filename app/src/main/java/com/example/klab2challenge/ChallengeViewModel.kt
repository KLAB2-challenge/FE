package com.example.klab2challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.db.repository.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()
    val myChallenges = challengeRepository.myChallenges.asLiveData()

    fun insert(challenge: ChallengeEntity) {
        viewModelScope.launch {
            challengeRepository.insert(challenge)
        }
    }

    fun getOfficialChallenges() {
        viewModelScope.launch {
            challengeRepository.getOfficialChallenges()
        }
    }
    fun getUserChallenges() {
        viewModelScope.launch {
            challengeRepository.getUserChallenges()
        }
    }
    fun getPopularChallenges() {
        viewModelScope.launch {
            challengeRepository.getPopularChallenges()
        }
    }
    fun getMyChallenges() {
        viewModelScope.launch {
            challengeRepository.getMyChallenges()
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