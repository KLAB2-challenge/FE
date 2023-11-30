package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.klab2challenge.db.entity.BorderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BorderDAO {
    @Insert
    fun addBorder(border : BorderEntity)

    @Query("UPDATE BorderTable SET isUnlocked = :iu WHERE number = :num")
    fun updateBorder(num : Int, iu: Boolean)

    @Delete
    fun deleteBorder(border : BorderEntity)

    @Query("SELECT * FROM BorderTable")
    fun getAllBorders() : Flow<List<BorderEntity>>

    @Query("DELETE FROM BorderTable")
    fun clearBorderTable()
}