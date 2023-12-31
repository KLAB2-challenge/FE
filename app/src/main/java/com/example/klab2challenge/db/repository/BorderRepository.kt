package com.example.klab2challenge.db.repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat.getColor
import com.example.klab2challenge.R
import com.example.klab2challenge.db.dao.BorderDAO
import com.example.klab2challenge.db.entity.BorderEntity
import com.example.klab2challenge.retrofit.ChangeCurrentBorderRequest
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class BorderRepository(private val borderDao: BorderDAO, private val retrofit: RetrofitInterface) {
    val borders = borderDao.getAllBorders()

    suspend fun insert(border: BorderEntity) {
        borderDao.addBorder(border)
    }

    suspend fun init(context: Context) {
        borderDao.clearBorderTable()
        borderDao.addBorder(
            BorderEntity(
                0,
                getColor(context, R.color.gold),
                "gold",
                20,
                false
            )
        )
        borderDao.addBorder(
            BorderEntity(
                1,
                getColor(context, R.color.green),
                "green",
                40,
                false
            )
        )
        borderDao.addBorder(
            BorderEntity(
                2,
                getColor(context, R.color.cherry),
                "cherry",
                60,
                false
            )
        )
        borderDao.addBorder(
            BorderEntity(
                3,
                getColor(context, R.color.blueberry),
                "blueberry",
                80,
                false
            )
        )
        borderDao.addBorder(
            BorderEntity(
                4,
                getColor(context, R.color.sunny),
                "sunny",
                100,
                false
            )
        )
        borderDao.addBorder(
            BorderEntity(
                5,
                getColor(context, R.color.rainy),
                "rainy",
                120,
                false
            )
        )
    }

    suspend fun requestBorder(userName: String, context: Context) {
        init(context)

        val borderResponse =
            retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
        if (borderResponse.isSuccessful) {
            val data = borderResponse.body()!!
            Log.d("Retrofit_requestBorder", data.toString())
            data.borderIds.forEach {
                borderDao.updateBorder(it, true)
            }
        } else {
            Log.d("Retrofit_requestBorder", borderResponse.message().toString())
        }
    }
}