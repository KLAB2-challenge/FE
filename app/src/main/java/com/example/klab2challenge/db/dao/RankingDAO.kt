package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.klab2challenge.db.entity.RankingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RankingDAO {
    @Insert
    fun addRanking(ranking : RankingEntity)

    @Update
    fun updateRanking(ranking : RankingEntity)

    @Delete
    fun deleteRanking(ranking : RankingEntity)

    @Query("SELECT * FROM rankingTable ORDER BY ranking")
    fun getRanking() : Flow<List<RankingEntity>>

    @Query("DELETE FROM rankingTable")
    fun clearRankingTable()
}