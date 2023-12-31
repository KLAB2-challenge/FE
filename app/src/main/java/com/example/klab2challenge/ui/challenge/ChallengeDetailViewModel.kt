package com.example.klab2challenge.ui.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.db.entity.RecordEntity
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.GetChallengeRequest
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetRelatedChallengesRequest
import com.example.klab2challenge.retrofit.JoinChallengeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengeDetailViewModel(
    val challengeRepository: ChallengeRepository,
    val userRepository: UserRepository
) : ViewModel() {
    private val _challengeDetailInfo = MutableLiveData<GetChallengeResponse>()
    private val _relatedChallenges = MutableLiveData<List<ChallengeEntity>>()
    private val _records = MutableLiveData<List<RecordEntity>>()

    val challengeDetailInfo: LiveData<GetChallengeResponse> get() = _challengeDetailInfo
    val relatedChallenges: LiveData<List<ChallengeEntity>> get() = _relatedChallenges
    val records: LiveData<List<RecordEntity>> get() = _records
    val users = userRepository.users.asLiveData()


    fun requestChallengeDetail(request: GetChallengeRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ret = challengeRepository.requestChallengeDetail(request)
                viewModelScope.launch {
                    _challengeDetailInfo.value = ret
                }.join()
            }
        }
    }

    fun requestRelatedChallenges(request: GetRelatedChallengesRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ret = challengeRepository.requestRelatedChallenges(request)
                viewModelScope.launch {
                    _relatedChallenges.value = ret!!.challenges.map { c ->
                        ChallengeEntity(
                            c.challengeId,
                            c.contents.title,
                            c.contents.image,
                            c.memberNum,
                            c.infos.startDate + " ~ " + c.infos.endDate,
                            c.infos.frequency,
                            c.progressRate,
                            c.join,
                            0
                        );
                    }
                }.join()
            }
        }
    }

    fun requestJoin(request: JoinChallengeRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeRepository.requestJoin(request)
                viewModelScope.launch {
                    val data = _challengeDetailInfo.value
                    data!!.join = true
                    _challengeDetailInfo.value = data!!
                }.join()
                challengeRepository.requestChallenges(request.memberName)
            }
        }
    }

    fun requestProofPosts(challengeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ret = challengeRepository.requestProofPosts(challengeId)
                viewModelScope.launch {
                    _records.value = ret.proofPosts.map { p ->
                        RecordEntity(
                            p.proofPostId,
                            p.memberName,
                            p.memberCurrentBorder,
                            p.memberImageUrl,
                            p.contents.title,
                            p.contents.content,
                            p.contents.image,
                            p.infos.date,
                            p.commentNum
                        )
                    }
                }.join()
            }
        }
    }

}