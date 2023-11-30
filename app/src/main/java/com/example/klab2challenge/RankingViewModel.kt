package com.example.klab2challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.RankingEntity
import com.example.klab2challenge.db.repository.RankingRepository
import kotlinx.coroutines.launch

class RankingViewModel(private val rankingRepository: RankingRepository) : ViewModel() {
    val rankings = rankingRepository.rankings.asLiveData()

    fun insert(ranking: RankingEntity) {
        viewModelScope.launch {
            rankingRepository.insert(ranking)
        }
    }


    fun requestRanking(userName: String) {
        viewModelScope.launch {
            rankingRepository.requestRanking(userName)
        }
    }
}