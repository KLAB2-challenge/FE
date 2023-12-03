package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.ChallengeDAO
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.retrofit.GetChallengeRequest
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetMemberAllChallengesRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.GetProofPostsResponse
import com.example.klab2challenge.retrofit.GetRelatedChallengesRequest
import com.example.klab2challenge.retrofit.GetRelatedChallengesResponse
import com.example.klab2challenge.retrofit.JoinChallengeRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.SetChallengeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class ChallengeRepository(
    private val challengeDao: ChallengeDAO,
    private val retrofit: RetrofitInterface
) {
    val popularChallenges = challengeDao.getPopularChallenges()
    val officialChallenges = challengeDao.getOfficialChallenges()
    val userChallenges = challengeDao.getUserChallenges()
    val myChallenges = challengeDao.getMyChallenges()

    suspend fun insert(challenge: ChallengeEntity) {
        challengeDao.addChallenge(challenge)
    }

    suspend fun init() {
        challengeDao.clearChallengeTable()
    }


    suspend fun requestOfficialChallenges(userName: String) {
        val officialChallengeResponse =
            retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, true))
        if (officialChallengeResponse.isSuccessful) {
            val data = officialChallengeResponse.body()!!
            Log.d("Retrofit_requestOfficialHcps", data.toString())
            data.challenges.forEach {
                challengeDao.addChallenge(
                    ChallengeEntity(
                        it.challengeId,
                        it.contents.title,
                        it.contents.image,
                        it.memberNum,
                        it.infos.startDate + " ~ " + it.infos.endDate,
                        it.infos.frequency,
                        it.progressRate,
                        it.join,
                        0
                    )
                )
            }
        } else {
            Log.d("Retrofit_requestOfficialHcps", officialChallengeResponse.message().toString())
        }
    }

    suspend fun requestUserChallenges(userName: String) {
        val userChallengeResponse =
            retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, false))
        if (userChallengeResponse.isSuccessful) {
            val data = userChallengeResponse.body()!!
            Log.d("Retrofit_requestUserHcps", data.toString())
            data.challenges.forEach {
                challengeDao.addChallenge(
                    ChallengeEntity(
                        it.challengeId,
                        it.contents.title,
                        it.contents.image,
                        it.memberNum,
                        it.infos.startDate + " ~ " + it.infos.endDate,
                        it.infos.frequency,
                        it.progressRate,
                        it.join,
                        1
                    )
                )
            }
        } else {
            Log.d("Retrofit_requestUserHcps", userChallengeResponse.message().toString())
        }
    }

    suspend fun requestPopularChallenges(userName: String) {
        val popularChallengeResponse =
            retrofit.getChallenge(GetPopularChallengesRequest(userName, 0, 5))
        if (popularChallengeResponse.isSuccessful) {
            val data = popularChallengeResponse.body()!!
            Log.d("Retrofit_requestPopularHcps", data.toString())
            data.challenges.forEach {
                challengeDao.addChallenge(
                    ChallengeEntity(
                        it.challengeId,
                        it.contents.title,
                        it.contents.image,
                        it.memberNum,
                        it.infos.startDate + " ~ " + it.infos.endDate,
                        it.infos.frequency,
                        it.progressRate,
                        it.join,
                        2
                    )
                )
            }
        } else {
            Log.d("Retrofit_requestPopularHcps", popularChallengeResponse.message().toString())
        }
    }

    suspend fun requestMyChallenges(userName: String) {
        val myChallengeResponse =
            retrofit.getChallenge(GetMemberAllChallengesRequest(userName, 0, 5))
        if (myChallengeResponse.isSuccessful) {
            val data = myChallengeResponse.body()!!
            Log.d("Retrofit_requestMyHcps", data.toString())
            data.challenges.forEach {
                challengeDao.addChallenge(
                    ChallengeEntity(
                        it.challengeId,
                        it.contents.title,
                        it.contents.image,
                        it.memberNum,
                        it.infos.startDate + " ~ " + it.infos.endDate,
                        it.infos.frequency,
                        it.progressRate,
                        it.join,
                        3
                    )
                )
            }
        } else {
            Log.d("Retrofit_requestMyHcps", myChallengeResponse.message().toString())
        }
    }


    suspend fun requestSetChallenge(image: MultipartBody.Part?, request: SetChallengeRequest) {
        val setChallengeResponse = retrofit.setChallenge(image, request)
        if (setChallengeResponse.isSuccessful) {
            val data = setChallengeResponse.body()!!
            Log.d("Retrofit_requestSetChallenge", data.toString())
        } else {
            Log.d("Retrofit_requestSetChallenge", setChallengeResponse.message().toString())
        }
    }

    suspend fun requestChallengeDetail(request: GetChallengeRequest): GetChallengeResponse {
        val challengeDetailResponse = retrofit.getChallenge(request)
        if (challengeDetailResponse.isSuccessful) {
            val data = challengeDetailResponse.body()!!
            return data
        } else {
            Log.d("retrofit_requestSetChallenge", challengeDetailResponse.message().toString())
            return challengeDetailResponse.body()!!
        }
    }

    suspend fun requestRelatedChallenges(request: GetRelatedChallengesRequest): GetRelatedChallengesResponse {
        val relatedChallengesResponse = retrofit.getChallenge(request)
        if (relatedChallengesResponse.isSuccessful) {
            val data = relatedChallengesResponse.body()!!
            return data
        } else {
            Log.d(
                "retrofit_requestSetChallenge",
                relatedChallengesResponse.message().toString()
            )
            return relatedChallengesResponse.body()!!
        }
    }

    suspend fun requestJoin(request: JoinChallengeRequest) {
        val joinResponse = retrofit.setChallenge(request)
        if (joinResponse.isSuccessful) {
            val data = joinResponse.body()!!
            Log.d("retrofit_requestJoin", data.toString())
        } else {
            Log.d("retrofit_requestJoin", joinResponse.message().toString())
        }
    }

    suspend fun requestProofPosts(challengeId: Int): GetProofPostsResponse {
        val proofPostsResponse = retrofit.getProofPosts(challengeId)
        if (proofPostsResponse.isSuccessful) {
            val data = proofPostsResponse.body()!!
            return data
        } else {
            Log.d("retrofit_requestJoin", proofPostsResponse.message().toString())
            return proofPostsResponse.body()!!
        }
    }

    suspend fun requestChallenges(userName: String) {
        init()
        requestPopularChallenges(userName)
        requestOfficialChallenges(userName)
        requestUserChallenges(userName)
        requestMyChallenges(userName)
    }
}