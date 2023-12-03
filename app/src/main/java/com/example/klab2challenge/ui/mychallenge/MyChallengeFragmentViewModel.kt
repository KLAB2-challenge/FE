package com.example.klab2challenge.ui.mychallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.UserRepository

class MyChallengeFragmentViewModel(
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val myChallenges = challengeRepository.myChallenges.asLiveData()
    val users = userRepository.users.asLiveData()
}