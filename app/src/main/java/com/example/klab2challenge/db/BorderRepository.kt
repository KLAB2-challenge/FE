package com.example.klab2challenge.db

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.asLiveData
import com.example.klab2challenge.R
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class BorderRepository(private val borderDao: BorderDAO, private val retrofit: RetrofitInterface) {
    val borders = borderDao.getAllBorders()

    @WorkerThread
    suspend fun insert(border: BorderEntity) {
        borderDao.addBorder(border)
    }

    @WorkerThread
    suspend fun init(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            borderDao.clearBorderTable()
            borderDao.addBorder(
                BorderEntity(
                    0,
                    getColor(context, R.color.gold),
                    "gold",
                    20,
                    false,
                    false
                )
            )
            borderDao.addBorder(
                BorderEntity(
                    1,
                    getColor(context, R.color.green),
                    "green",
                    40,
                    false,
                    false
                )
            )
            borderDao.addBorder(
                BorderEntity(
                    2,
                    getColor(context, R.color.cherry),
                    "cherry",
                    60,
                    false,
                    false
                )
            )
            borderDao.addBorder(
                BorderEntity(
                    3,
                    getColor(context, R.color.blueberry),
                    "blueberry",
                    80,
                    false,
                    false
                )
            )
            borderDao.addBorder(
                BorderEntity(
                    4,
                    getColor(context, R.color.sunny),
                    "sunny",
                    100,
                    false,
                    false
                )
            )
            borderDao.addBorder(
                BorderEntity(
                    5,
                    getColor(context, R.color.rainy),
                    "rainy",
                    120,
                    false,
                    false
                )
            )
        }
    }

    @WorkerThread
    suspend fun requestBorder(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val borderResponse = retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
            if (borderResponse.isSuccessful) {
                val data = borderResponse.body()!!
                data.borderIds.forEach {
                    borderDao.updateBorder(it, true)
                }
            } else {
                Log.d("retrofit_requestBorder", borderResponse.message().toString())
            }
        }
    }
}