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

    suspend fun insert(record: RecordEntity) {
        recordDAO.addRecord(record)
    }

    suspend fun init() {
        recordDAO.clearRecordTable()
    }


    suspend fun requestRecords(challengeId: Int) {
        init()

        val recordsResponse =
            retrofit.getProofPosts(challengeId)
        if (recordsResponse.isSuccessful) {
            val data = recordsResponse.body()!!
            Log.d("Retrofit_requestRecords", data.toString())
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
            Log.d("Retrofit_requestRecords", recordsResponse.message().toString())
        }
    }

    suspend fun requestSetRecord(image: MultipartBody.Part?, request: SetProofPostRequest) {
        val recordResponse = retrofit.setProofPost(image, request)
        if (recordResponse.isSuccessful) {
            val data = recordResponse.body()!!
            Log.d("Retrofit_requestSetRecord", data.toString())
        } else {
            Log.d("Retrofit_requestSetRecord", recordResponse.message().toString())
        }
    }

    suspend fun requestRecord(recordId: Int): GetProofPostResponse {
        val recordResponse = retrofit.getProofPost(recordId)
        if (recordResponse.isSuccessful) {
            val data = recordResponse.body()!!
            return data
        } else {
            Log.d("Retrofit_requestRecord", recordResponse.message().toString())
            return recordResponse.body()!!
        }
    }

    suspend fun requestComments(recordId: Int): GetAllCommentsResponse {
        val commentsResponse = retrofit.getAllComments(recordId)
        if (commentsResponse.isSuccessful) {
            val data = commentsResponse.body()!!
            Log.d("Retrofit_requestComments", data.toString())
            return data
        } else {
            Log.d("Retrofit_requestComments", commentsResponse.message().toString())
            return commentsResponse.body()!!
        }
    }

    suspend fun requestSetComment(userName: String, recordId: Int, content: String) {
        val commentResponse =
            retrofit.setComment(SetCommentRequest(userName, content, recordId))
        if (commentResponse.isSuccessful) {
            val data = commentResponse.body()!!
            Log.d("Retrofit_requestSetComment", data.toString())
        } else {
            Log.d("Retrofit_requestSetComment", commentResponse.message().toString())
        }
    }


    suspend fun requestUpdateCommentNum(recordId: Int, commentNum: Int) {
        recordDAO.updateRecordCommentNum(recordId, commentNum)
    }

}