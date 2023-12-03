package com.example.klab2challenge.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.BorderEntity
import com.example.klab2challenge.db.entity.UserEntity
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class MyPageFragmentViewModel(
    private val userRepository: UserRepository,
    private val borderRepository: BorderRepository
) : ViewModel() {
    var borders = borderRepository.borders.asLiveData()
    var users = userRepository.users.asLiveData()
}