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
import com.example.klab2challenge.db.repository.RecordRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.ProofPostContents
import com.example.klab2challenge.retrofit.ProofPostInfos
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecordDetailViewModel(
    val userRepository: UserRepository,
    val recordRepository: RecordRepository,
    val borderRepository: BorderRepository
) : ViewModel() {
    private val _recordInfo = MutableLiveData<GetProofPostResponse>()
    private val _comments = MutableLiveData<List<CommentEntity>>()

    val recordInfo: LiveData<GetProofPostResponse> get() = _recordInfo
    val comments: LiveData<List<CommentEntity>> get() = _comments
    val users = userRepository.users.asLiveData()
    val borders = borderRepository.borders.asLiveData()

    fun requestRecord(recordId: Int) {
        viewModelScope.launch {
            val ret = recordRepository.requestRecord(recordId)
            _recordInfo.value = ret
        }
    }

    fun requestComments(recordId: Int) {
        viewModelScope.launch {
            val ret = recordRepository.requestComments(recordId)
            _comments.value = ret.getCommentResponses.map { c ->
                CommentEntity(
                    c.memberName,
                    c.memberCurrentBorder,
                    c.memberImageUrl,
                    c.content,
                    c.infos.date
                )
            }
        }
    }

    fun requestSetComment(challengeId: Int, recordId: Int, content: String) {
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            recordRepository.requestSetComment(userInfo.name, recordId, content)
            delay(100)
            val ret = recordRepository.requestComments(recordId)
            _comments.value = ret.getCommentResponses.map { c ->
                CommentEntity(
                    c.memberName,
                    c.memberCurrentBorder,
                    c.memberImageUrl,
                    c.content,
                    c.infos.date
                )
            }
            delay(100)
            userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, 10)
            delay(100)
            recordRepository.requestUpdateCommentNum(recordId, _comments.value!!.size)
            delay(100)
            recordRepository.refreshRecords(challengeId)
        }
    }

}