package com.example.klab2challenge.ui.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.db.entity.CommentEntity
import com.example.klab2challenge.db.entity.RecordEntity
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.RecordRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.ProofPostContents
import com.example.klab2challenge.retrofit.ProofPostInfos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RecordDetailViewModel(
    val userRepository: UserRepository,
    val recordRepository: RecordRepository,
    val borderRepository: BorderRepository,
    val rankingRepository: RankingRepository
) : ViewModel() {
    private val _recordInfo = MutableLiveData<GetProofPostResponse>()
    private val _comments = MutableLiveData<List<CommentEntity>>()

    val recordInfo: LiveData<GetProofPostResponse> get() = _recordInfo
    val comments: LiveData<List<CommentEntity>> get() = _comments
    val users = userRepository.users.asLiveData()
    val borders = borderRepository.borders.asLiveData()

    fun requestRecord(recordId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ret = recordRepository.requestRecord(recordId)
                viewModelScope.launch {
                    _recordInfo.value = ret
                }.join()
            }
        }
    }

    fun requestComments(recordId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ret = recordRepository.requestComments(recordId)
                viewModelScope.launch {
                    _comments.value = ret.getCommentResponses.map { c ->
                        CommentEntity(
                            c.memberName,
                            c.memberCurrentBorder,
                            c.memberImageUrl,
                            c.content,
                            c.infos.date
                        )
                    }
                }.join()
            }
        }
    }

    fun requestSetComment(userName: String, challengeId: Int, recordId: Int, content: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setComment(userName, challengeId, recordId, content)
            }
        }
    }

    suspend fun setComment(userName: String, challengeId: Int, recordId: Int, content: String) {
        val userInfo = users.value!!.get(0)
        recordRepository.requestSetComment(userInfo.name, recordId, content)
        val ret = recordRepository.requestComments(recordId)
        viewModelScope.launch {
            _comments.value = ret.getCommentResponses.map { c ->
                CommentEntity(
                    c.memberName,
                    c.memberCurrentBorder,
                    c.memberImageUrl,
                    c.content,
                    c.infos.date
                )
            }
        }.join()
        userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, 10)
        recordRepository.requestUpdateCommentNum(recordId, _comments.value!!.size)
        recordRepository.requestRecords(challengeId)
        userRepository.requestUser(userName)
        rankingRepository.requestRanking(userName)
    }

}