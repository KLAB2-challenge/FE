package com.example.klab2challenge.ui.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RecordRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.SetChallengeRequest
import com.example.klab2challenge.retrofit.SetProofPostRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddRecordViewModel(
    private val userRepository: UserRepository,
    private val challengeRepository: ChallengeRepository,
    private val recordRepository: RecordRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()
    val records = recordRepository.records.asLiveData()

    fun requestSetRecord(image : MultipartBody.Part?, request : SetProofPostRequest) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            recordRepository.requestSetRecord(image, request)
            delay(100)
            val userInfo = users.value!!.get(0)
            userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, 20)
        }
    }

}