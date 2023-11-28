package com.example.klab2challenge.db

import androidx.annotation.WorkerThread
import retrofit2.Retrofit

class BorderRepository(private val borderDao : BorderDAO, private val retrofit: Retrofit) {
    val borders = borderDao.getAllBorders()

    @WorkerThread
    suspend fun insert(border : BorderEntity) {
        borderDao.addBorder(border)
    }
}