package com.example.klab2challenge.db.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.klab2challenge.db.dao.UserDAO
import com.example.klab2challenge.db.entity.UserEntity
import com.example.klab2challenge.retrofit.BuyBorderRequest
import com.example.klab2challenge.retrofit.ChangeCurrentBorderRequest
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.SetMemberCoinsRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDAO, private val retrofit: RetrofitInterface) {
    val users = userDao.getUser()

    @WorkerThread
    fun insert(user: UserEntity) {
        userDao.addUser(user)
    }

    @WorkerThread
    fun init() {
        userDao.clearUserTable()
    }

    @WorkerThread
    fun requestUser(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            init()
            delay(100)
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
            delay(100)
            val allBorderResponse =
                retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
            if (allBorderResponse.isSuccessful) {
                val data = allBorderResponse.body()!!
                userData.ownBorders = data.borderIds.toString()
            } else {
                Log.d("retrofit_requestAllBorders", allBorderResponse.message().toString())
            }
            delay(100)
            val rankingResponse = retrofit.getRanking(userName)
            if (rankingResponse.isSuccessful) {
                val data = rankingResponse.body()!!
                userData.ranking = data.myRank
            } else {
                Log.d("retrofit_requestRanking", rankingResponse.message().toString())
            }
            delay(100)
            Log.d("roomuserInfo", userData.toString())
            userDao.addUser(userData)
        }
    }

    @WorkerThread
    fun requestChangeBorder(userName: String, checkedBorder: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val changeCurrentBorderResponse = retrofit.changeCurrentBorder(
                ChangeCurrentBorderRequest(userName, checkedBorder)
            )
            if (changeCurrentBorderResponse.isSuccessful) {
                val data = changeCurrentBorderResponse.body()!!
            } else {
                Log.d(
                    "retrofit_border_changeBorder",
                    changeCurrentBorderResponse.message().toString()
                )
            }
        }
    }

    @WorkerThread
    fun requestBuyBorder(userName: String, currentBorder: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val buyBorderResponse =
                retrofit.buyBorder(BuyBorderRequest(userName, currentBorder))
            if (buyBorderResponse.isSuccessful) {
                val data = buyBorderResponse.body()!!
                Log.d("retrofit_border_buy", data.success.toString())
            } else {
                Log.d("retrofit_border_buy", buyBorderResponse.message().toString())
            }
        }
    }

    @WorkerThread
    fun requestSetCoin(userName: String, currentCoin: Int, offset: Int) {
        Log.d("helloooo?", "abc")
        Log.d("helloooo?", offset.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val setMemberCoinResponse = retrofit.setMemberCoins(
                SetMemberCoinsRequest(
                    userName,
                    offset
                )
            )
            if (setMemberCoinResponse.isSuccessful) {
                val data = setMemberCoinResponse.body()!!
                Log.d("helloooo?", "abc")
                Log.d("helloooo?", offset.toString())
                Log.d("retrofit_border_setCoin", data.success.toString())
            } else {
                Log.d("retrofit_border_setCoin", setMemberCoinResponse.message().toString())
            }
        }
    }
}