package com.example.klab2challenge.db

import androidx.annotation.WorkerThread
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import retrofit2.Retrofit

class UserRepository(private val userDao : UserDAO, private val retrofit: RetrofitInterface) {
    val users = userDao.getUser()

    @WorkerThread
    suspend fun insert(user : UserEntity) {
        userDao.addUser(user)
    }

    @WorkerThread
    suspend fun requestUser(userName : String) {
        retrofit.getMemberInfos(GetMemberInfosRequest(userName))
    }
}