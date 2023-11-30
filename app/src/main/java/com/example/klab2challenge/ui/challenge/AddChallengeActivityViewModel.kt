package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.SetChallengeRequest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddChallengeActivityViewModel(
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()

    fun requestSetChallenge(image : MultipartBody.Part?, request : SetChallengeRequest) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            challengeRepository.requestSetChallenge(image, request)
        }
    }

}