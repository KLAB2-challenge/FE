package com.example.klab2challenge.ui.ranking

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.BorderEntity
import com.example.klab2challenge.db.entity.RankingEntity
import com.example.klab2challenge.db.entity.UserEntity
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class RankingFragmentViewModel(
    private val userRepository: UserRepository,
    private val rankingRepository: RankingRepository,
    private val borderRepository: BorderRepository
) : ViewModel() {
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
}