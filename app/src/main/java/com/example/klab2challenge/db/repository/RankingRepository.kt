package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.RankingDAO
import com.example.klab2challenge.db.entity.RankingEntity
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RankingRepository(
    private val rankingDao: RankingDAO,
    private val retrofit: RetrofitInterface
) {
    val rankings = rankingDao.getRanking()

    @WorkerThread
    suspend fun insert(ranking: RankingEntity) {
        rankingDao.addRanking(ranking)
    }

    @WorkerThread
    suspend fun init() {
        rankingDao.clearRankingTable()
    }


    @WorkerThread
    suspend fun requestRanking(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val rankingResponse = retrofit.getRanking(userName)
            if (rankingResponse.isSuccessful) {
                val data = rankingResponse.body()!!
                for (i in 0..<data.ranker.size) {
                    val rankerUser = data.ranker[i]
                    rankingDao.addRanking(
                        RankingEntity(
                            rankerUser.name,
                            rankerUser.infos.imageUrl,
                            rankerUser.infos.currentBorder,
                            rankerUser.infos.totalCoins,
                            i
                        )
                    )
                }
            } else {
                Log.d("retrofit_requestRanking", rankingResponse.message().toString())
            }
        }
    }

}