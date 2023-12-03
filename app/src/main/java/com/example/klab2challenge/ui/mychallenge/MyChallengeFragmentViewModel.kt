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
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val myChallenges = challengeRepository.myChallenges.asLiveData()
    val users = userRepository.users.asLiveData()

}