package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.SetChallengeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class AddChallengeActivityViewModel(
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()

    fun requestSetChallenge(image: MultipartBody.Part?, request: SetChallengeRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setChallenge(image, request)
            }
        }
    }

    suspend fun setChallenge(image: MultipartBody.Part?, request: SetChallengeRequest) {
        val userInfo = users.value!!.get(0)
        challengeRepository.requestSetChallenge(image, request)
        challengeRepository.requestChallenges(userInfo.name)
    }

}