package com.example.klab2challenge.db

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.retrofit.GetMemberAllChallengesRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.RetrofitInterface

class ChallengeRepository(private val challengeDao : ChallengeDAO, private val retrofit: RetrofitInterface) {
    val popularChallenges = challengeDao.getPopularChallenges()
    val officialChallenges = challengeDao.getOfficialChallenges()
    val userChallenges = challengeDao.getUserChallenges()
    val myChallenges = challengeDao.getMyChallenges()

    @WorkerThread
    suspend fun insert(challenge : ChallengeEntity) {
        challengeDao.addChallenge(challenge)
    }


    @WorkerThread
    suspend fun requestOfficialChallenges(userName : String) {
        val officialChallengeResponse = retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, true))
        if(officialChallengeResponse.isSuccessful) {
            val data = officialChallengeResponse.body()!!
            data.challenges.forEach {
                challengeDao.addChallenge(ChallengeEntity(it.challengeId, it.contents.title, it.contents.image, it.memberNum,
                    it.infos.startDate + " ~ " + it.infos.endDate, it.infos.frequency, it.progressRate, it.join, 0))
            }
        } else {
            Log.d("retrofit_requestPopularHcps", officialChallengeResponse.message().toString())
        }
    }

    @WorkerThread
    suspend fun requestUserChallenges(userName : String) {
        val userChallengeResponse = retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, false))
        if(userChallengeResponse.isSuccessful) {
            val data = userChallengeResponse.body()!!
            data.challenges.forEach {
                challengeDao.addChallenge(ChallengeEntity(it.challengeId, it.contents.title, it.contents.image, it.memberNum,
                    it.infos.startDate + " ~ " + it.infos.endDate, it.infos.frequency, it.progressRate, it.join, 1))
            }
        } else {
            Log.d("retrofit_requestPopularHcps", userChallengeResponse.message().toString())
        }
    }

    @WorkerThread
    suspend fun requestPopularChallenges(userName : String) {
        val popularChallengeResponse = retrofit.getChallenge(GetPopularChallengesRequest(userName, 0, 5))
        if(popularChallengeResponse.isSuccessful) {
            val data = popularChallengeResponse.body()!!
            data.challenges.forEach {
                challengeDao.addChallenge(ChallengeEntity(it.challengeId, it.contents.title, it.contents.image, it.memberNum,
                    it.infos.startDate + " ~ " + it.infos.endDate, it.infos.frequency, it.progressRate, it.join, 2))
            }
        } else {
            Log.d("retrofit_requestPopularHcps", popularChallengeResponse.message().toString())
        }
    }

    @WorkerThread
    suspend fun requestMyChallenges(userName : String) {
        val myChallengeResponse = retrofit.getChallenge(GetMemberAllChallengesRequest(userName, 0, 5))
        if(myChallengeResponse.isSuccessful) {
            val data = myChallengeResponse.body()!!
            data.challenges.forEach {
                challengeDao.addChallenge(ChallengeEntity(it.challengeId, it.contents.title, it.contents.image, it.memberNum,
                    it.infos.startDate + " ~ " + it.infos.endDate, it.infos.frequency, it.progressRate, it.join, 2))
            }
        } else {
            Log.d("retrofit_requestPopularHcps", myChallengeResponse.message().toString())
        }
    }
}