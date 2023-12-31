package com.example.klab2challenge.ui.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.RecordRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.SetChallengeRequest
import com.example.klab2challenge.retrofit.SetProofPostRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class AddRecordViewModel(
    private val userRepository: UserRepository,
    private val recordRepository: RecordRepository,
    private val rankingRepository: RankingRepository
) : ViewModel() {
    val users = userRepository.users.asLiveData()
    val records = recordRepository.records.asLiveData()

    fun requestSetRecord(image: MultipartBody.Part?, request: SetProofPostRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setRecord(image, request)
            }
        }
    }

    suspend fun setRecord(image: MultipartBody.Part?, request: SetProofPostRequest) {
        val userInfo = users.value!!.get(0)
        recordRepository.requestSetRecord(image, request)
        recordRepository.requestRecords(request.challengeID)
        userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, 20)
        userRepository.requestUser(userInfo.name)
        rankingRepository.requestRanking(userInfo.name)
    }

}