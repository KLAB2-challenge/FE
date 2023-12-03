package com.example.klab2challenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.UserRepository

class HomeFragmentViewModel(
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()
    val popularChallenges = challengeRepository.popularChallenges.asLiveData()
    val userChallenges = challengeRepository.userChallenges.asLiveData()
    val officialChallenges = challengeRepository.officialChallenges.asLiveData()
}