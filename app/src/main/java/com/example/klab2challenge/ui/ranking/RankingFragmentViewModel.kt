package com.example.klab2challenge.ui.ranking


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository

class RankingFragmentViewModel(
    private val userRepository: UserRepository,
    private val rankingRepository: RankingRepository,
    private val borderRepository: BorderRepository
) : ViewModel() {
    val borders = borderRepository.borders.asLiveData()
    val users = userRepository.users.asLiveData()
    val rankings = rankingRepository.rankings.asLiveData()
}