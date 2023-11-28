package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RankingDAO {
    @Insert
    fun addRanking(hcp : RankingEntity)

    @Update
    fun updateRanking(hcp : RankingEntity)

    @Delete
    fun deleteRanking(hcp : RankingEntity)

    @Query("SELECT * FROM rankingTable ORDER BY ranking")
    fun getRanking() : List<RankingEntity>
}