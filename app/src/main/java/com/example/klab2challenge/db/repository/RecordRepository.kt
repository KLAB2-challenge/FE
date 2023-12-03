package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.RecordDAO
import com.example.klab2challenge.db.entity.RecordEntity
import com.example.klab2challenge.retrofit.GetAllCommentsResponse
import com.example.klab2challenge.retrofit.GetCommentResponse
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.SetCommentRequest
import com.example.klab2challenge.retrofit.SetProofPostRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody

class RecordRepository(
    private val recordDAO: RecordDAO,
    private val retrofit: RetrofitInterface
) {
    val records = recordDAO.getRecords()

    @WorkerThread
    suspend fun insert(record: RecordEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            recordDAO.addRecord(record)
        }
    }

    @WorkerThread
    suspend fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            recordDAO.clearRecordTable()
        }
    }


    @WorkerThread
    suspend fun requestRecords(challengeId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            runBlocking {
                init()
                delay(100)
                val recordsResponse =
                    retrofit.getProofPosts(challengeId)
                if (recordsResponse.isSuccessful) {
                    val data = recordsResponse.body()!!
                    data.proofPosts.forEach {
                        recordDAO.addRecord(
                            RecordEntity(
                                it.proofPostId,
                                it.memberName,
                                it.memberCurrentBorder,
                                it.memberImageUrl,
                                it.contents.title,
                                it.contents.content,
                                it.contents.image,
                                it.infos.date,
                                it.commentNum
                            )
                        )
                    }
                } else {
                    Log.d("retrofit_requestRecords", recordsResponse.message().toString())
                }
            }
        }
    }

    @WorkerThread
    suspend
    fun requestSetRecord(image: MultipartBody.Part?, request: SetProofPostRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val recordResponse = retrofit.setProofPost(image, request)
            if (recordResponse.isSuccessful) {
                val data = recordResponse.body()!!
                Log.d("retrofit_requestSetRecord", data.toString())
            } else {
                Log.d("retrofit_requestSetRecord", recordResponse.message().toString())
            }
        }
    }

    @WorkerThread
    suspend fun requestRecord(recordId: Int): GetProofPostResponse {
        val ret = CoroutineScope(Dispatchers.IO).async {
            val recordResponse = retrofit.getProofPost(recordId)
            if (recordResponse.isSuccessful) {
                val data = recordResponse.body()!!
                data
            } else {
                Log.d("retrofit_requestRecord", recordResponse.message().toString())
                recordResponse.body()!!
            }
        }
        return ret.await()
    }

    @WorkerThread
    suspend fun requestComments(recordId: Int): GetAllCommentsResponse {
        val ret = CoroutineScope(Dispatchers.IO).async {
            val commentsResponse = retrofit.getAllComments(recordId)
            if (commentsResponse.isSuccessful) {
                val data = commentsResponse.body()!!
                data
            } else {
                Log.d("retrofit_requestComments", commentsResponse.message().toString())
                commentsResponse.body()!!
            }
        }
        return ret.await()
    }

    @WorkerThread
    suspend fun requestSetComment(userName: String, recordId: Int, content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val commentResponse =
                retrofit.setComment(SetCommentRequest(userName, content, recordId))
            if (commentResponse.isSuccessful) {
                val data = commentResponse.body()!!
                Log.d("retrofit_requestSetComment", data.toString())
            } else {
                Log.d("retrofit_requestSetComment", commentResponse.message().toString())
            }
        }
    }


    @WorkerThread
    suspend fun requestUpdateCommentNum(recordId: Int, commentNum: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            recordDAO.updateRecordCommentNum(recordId, commentNum)
            Log.d("updatecomment", recordId.toString() + " " + commentNum.toString())
        }
    }

}