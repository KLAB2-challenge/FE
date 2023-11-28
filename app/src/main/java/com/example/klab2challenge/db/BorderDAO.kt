package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BorderDAO {
    @Insert
    fun addBorder(hcp : BorderEntity)

    @Update
    fun updateBorder(hcp : BorderEntity)

    @Delete
    fun deleteBorder(hcp : BorderEntity)

    @Query("SELECT * FROM BorderTable")
    fun getAllBorders() : List<BorderEntity>
}