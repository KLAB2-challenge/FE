package com.example.klab2challenge.db

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.retrofit.RetrofitInterface
import retrofit2.Retrofit

class RankingRepository(private val rankingDao : RankingDAO, private val retrofit: RetrofitInterface) {
    val rankings = rankingDao.getRanking()

    @WorkerThread
    suspend fun insert(ranking : RankingEntity) {
        rankingDao.addRanking(ranking)
    }

    @WorkerThread
    suspend fun requestRanking(userName : String) {
        val rankingResponse = retrofit.getRanking(userName)
        if(rankingResponse.isSuccessful) {
            val data = rankingResponse.body()!!

        } else {
            Log.d("retrofit_requestRanking", rankingResponse.message().toString())
        }
    }

}