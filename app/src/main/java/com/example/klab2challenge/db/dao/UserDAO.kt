package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.klab2challenge.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    fun addUser(user : UserEntity)

    @Update
    fun updateUser(user : UserEntity)

    @Delete
    fun deleteUser(user : UserEntity)

    @Query("SELECT * FROM UserTable")
    fun getUser() : Flow<List<UserEntity>>

    @Query("DELETE FROM UserTable")
    fun clearUserTable()
}