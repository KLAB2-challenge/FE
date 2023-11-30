package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.ChallengeDAO
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.retrofit.GetMemberAllChallengesRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChallengeRepository(
    private val challengeDao: ChallengeDAO,
    private val retrofit: RetrofitInterface
) {
    val popularChallenges = challengeDao.getPopularChallenges()
    val officialChallenges = challengeDao.getOfficialChallenges()
    val userChallenges = challengeDao.getUserChallenges()
    val myChallenges = challengeDao.getMyChallenges()

    @WorkerThread
    suspend fun insert(challenge: ChallengeEntity) {
        CoroutineScope(Dispatchers.IO).launch {

            challengeDao.addChallenge(challenge)
        }
    }

    @WorkerThread
    suspend fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            challengeDao.clearChallengeTable()
        }
    }


    @WorkerThread
    suspend fun requestOfficialChallenges(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val officialChallengeResponse =
                retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, true))
            if (officialChallengeResponse.isSuccessful) {
                val data = officialChallengeResponse.body()!!
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
                Log.d("retrofit_requestPopularHcps", officialChallengeResponse.message().toString())
            }
        }
    }

    @WorkerThread
    suspend fun getOfficialChallenges() {
        CoroutineScope(Dispatchers.IO).launch {
            challengeDao.getOfficialChallenges()
        }
    }

    @WorkerThread
    suspend fun requestUserChallenges(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val userChallengeResponse =
                retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, false))
            if (userChallengeResponse.isSuccessful) {
                val data = userChallengeResponse.body()!!
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
                Log.d("retrofit_requestPopularHcps", userChallengeResponse.message().toString())
            }
        }
    }

    @WorkerThread
    suspend fun getUserChallenges() {
        CoroutineScope(Dispatchers.IO).launch {

        challengeDao.getUserChallenges()
        }
    }

    @WorkerThread
    suspend fun requestPopularChallenges(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val popularChallengeResponse =
                retrofit.getChallenge(GetPopularChallengesRequest(userName, 0, 5))
            if (popularChallengeResponse.isSuccessful) {
                val data = popularChallengeResponse.body()!!
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
                Log.d("retrofit_requestPopularHcps", popularChallengeResponse.message().toString())
            }
        }
    }

    @WorkerThread
    suspend fun getPopularChallenges() {
        CoroutineScope(Dispatchers.IO).launch {

            challengeDao.getPopularChallenges()
        }
    }

    @WorkerThread
    suspend fun requestMyChallenges(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val myChallengeResponse =
                retrofit.getChallenge(GetMemberAllChallengesRequest(userName, 0, 5))
            if (myChallengeResponse.isSuccessful) {
                val data = myChallengeResponse.body()!!
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
                Log.d("retrofit_requestPopularHcps", myChallengeResponse.message().toString())
            }
        }
    }

    @WorkerThread
    suspend fun getMyChallenges() {
        CoroutineScope(Dispatchers.IO).launch {

            challengeDao.getMyChallenges()
        }
    }
}