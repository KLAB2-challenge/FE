package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    fun addUser(user : UserEntity)

    @Update
    fun updateUser(user : UserEntity)

    @Delete
    fun deleteUser(user : UserEntity)

    @Query("SELECT * FROM UserTable")
    fun getUser() : List<UserEntity>
}