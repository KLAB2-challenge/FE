package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BorderDAO {
    @Insert
    fun addBorder(border : BorderEntity)

    @Query("UPDATE bordertable SET isLocked = :il WHERE number = :num")
    fun updateBorder(num : Int, il: Boolean)

    @Delete
    fun deleteBorder(border : BorderEntity)

    @Query("SELECT * FROM BorderTable")
    fun getAllBorders() : Flow<List<BorderEntity>>

    @Query("DELETE FROM BorderTable")
    fun clearBorderTable()
}