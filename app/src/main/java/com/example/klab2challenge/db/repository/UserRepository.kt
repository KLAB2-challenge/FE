package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.UserDAO
import com.example.klab2challenge.db.entity.UserEntity
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDAO, private val retrofit: RetrofitInterface) {
    val users = userDao.getUser()

    @WorkerThread
    suspend fun insert(user: UserEntity) {
        userDao.addUser(user)
    }

    @WorkerThread
    suspend fun init() {
        userDao.clearUserTable()
    }

    @WorkerThread
    suspend fun requestUser(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userData = UserEntity(userName, "", 0, "", 0, 0, 0)
            val memberInfoResponse = retrofit.getMemberInfos(GetMemberInfosRequest(userName))
            if (memberInfoResponse.isSuccessful) {
                val data = memberInfoResponse.body()!!
                userData.image = data.infos.imageUrl
                userData.currentBorder = data.infos.currentBorder
                userData.currentCoin = data.infos.holdingCoins
                userData.totalCoin = data.infos.totalCoins
            } else {
                Log.d("retrofit_requestMemberInfo", memberInfoResponse.message().toString())
            }

            val allBorderResponse =
                retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
            if (allBorderResponse.isSuccessful) {
                val data = allBorderResponse.body()!!
                userData.ownBorders = data.borderIds.toString()
            } else {
                Log.d("retrofit_requestAllBorders", allBorderResponse.message().toString())
            }

            val rankingResponse = retrofit.getRanking(userName)
            if (rankingResponse.isSuccessful) {
                val data = rankingResponse.body()!!
                userData.ranking = data.myRank
            } else {
                Log.d("retrofit_requestRanking", rankingResponse.message().toString())
            }

            Log.d("roomuserInfo", userData.toString())
            userDao.addUser(userData)
        }
    }

    @WorkerThread
    suspend fun getUser() {
        userDao.getUser()
    }
}