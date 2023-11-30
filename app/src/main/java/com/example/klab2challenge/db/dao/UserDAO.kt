package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.klab2challenge.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    fun addUser(user : UserEntity)

    @Query("UPDATE UserTable SET currentBorder = :checkedBorder WHERE name = :userName")
    fun updateUserBorder(userName: String, checkedBorder: Int)

    @Query("UPDATE UserTable SET currentCoin = :coin WHERE name = :userName")
    fun updateUserCoin(userName: String, coin: Int)

    @Delete
    fun deleteUser(user : UserEntity)

    @Query("SELECT * FROM UserTable")
    fun getUser() : Flow<List<UserEntity>>

    @Query("DELETE FROM UserTable")
    fun clearUserTable()
}