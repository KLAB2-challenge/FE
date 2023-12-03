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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDAO, private val retrofit: RetrofitInterface) {
    val users = userDao.getUser()

    fun insert(user: UserEntity) {
        userDao.addUser(user)
    }

    fun init() {
        userDao.clearUserTable()
    }

    suspend fun requestUser(userName: String) {
        init()

        val userData = UserEntity(userName, "", 0, "", 0, 0, 0)
        val memberInfoResponse = retrofit.getMemberInfos(GetMemberInfosRequest(userName))
        if (memberInfoResponse.isSuccessful) {
            val data = memberInfoResponse.body()!!
            Log.d("Retrofit_requestMemberInfo", data.toString())
            userData.image = data.infos.imageUrl
            userData.currentBorder = data.infos.currentBorder
            userData.currentCoin = data.infos.holdingCoins
            userData.totalCoin = data.infos.totalCoins
        } else {
            Log.d("Retrofit_requestMemberInfo", memberInfoResponse.message().toString())
        }

        val allBorderResponse =
            retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
        if (allBorderResponse.isSuccessful) {
            val data = allBorderResponse.body()!!
            Log.d("Retrofit_requestAllBorders", data.toString())
            userData.ownBorders = data.borderIds.toString()
        } else {
            Log.d("Retrofit_requestAllBorders", allBorderResponse.message().toString())
        }

        val rankingResponse = retrofit.getRanking(userName)
        if (rankingResponse.isSuccessful) {
            val data = rankingResponse.body()!!
            Log.d("Retrofit_requestRanking", data.toString())
            userData.ranking = data.myRank
        } else {
            Log.d("Retrofit_requestRanking", rankingResponse.message().toString())
        }

        userDao.addUser(userData)
    }

    suspend fun requestChangeBorder(userName: String, checkedBorder: Int) {
            val changeCurrentBorderResponse = retrofit.changeCurrentBorder(
                ChangeCurrentBorderRequest(userName, checkedBorder)
            )
            if (changeCurrentBorderResponse.isSuccessful) {
                val data = changeCurrentBorderResponse.body()!!
                Log.d(
                    "Retrofit_changeBorder",
                    data.toString()
                )
            } else {
                Log.d(
                    "Retrofit_changeBorder",
                    changeCurrentBorderResponse.message().toString()
                )
            }
    }

    suspend fun requestBuyBorder(userName: String, currentBorder: Int, price: Int) {
            val buyBorderResponse =
                retrofit.buyBorder(BuyBorderRequest(userName, currentBorder, price))
            if (buyBorderResponse.isSuccessful) {
                val data = buyBorderResponse.body()!!
                Log.d("retrofit_border_buy", data.success.toString())
            } else {
                Log.d("retrofit_border_buy", buyBorderResponse.message().toString())
            }
    }

    suspend fun requestSetCoin(userName: String, currentCoin: Int, offset: Int) {
            val setMemberCoinResponse = retrofit.setMemberCoins(
                SetMemberCoinsRequest(
                    userName,
                    offset
                )
            )
            if (setMemberCoinResponse.isSuccessful) {
                val data = setMemberCoinResponse.body()!!
                Log.d("Retrofit_border_setCoin", data.toString())
            } else {
                Log.d("Retrofit_border_setCoin", setMemberCoinResponse.message().toString())
            }
    }
}