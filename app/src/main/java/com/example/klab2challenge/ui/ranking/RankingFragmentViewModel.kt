package com.example.klab2challenge.ui.ranking

import android.content.Context
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

    fun requestRanking() {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            rankingRepository.requestRanking(userInfo.name)
        }
    }

    fun requestUser() {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            userRepository.requestUser(userInfo.name)
        }
    }

    fun requestBorder(context : Context) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            borderRepository.requestBorder(userInfo.name, context)
        }
    }
}