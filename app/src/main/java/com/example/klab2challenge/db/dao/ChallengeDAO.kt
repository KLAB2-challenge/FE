package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.klab2challenge.db.entity.ChallengeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDAO {
    @Insert
    fun addChallenge(challenge : ChallengeEntity)

    @Update
    fun updateChallenge(challenge : ChallengeEntity)

    @Delete
    fun deleteChallenge(challenge : ChallengeEntity)

    @Query("SELECT * FROM ChallengeTable WHERE type == 0")
    fun getOfficialChallenges() : Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM ChallengeTable WHERE type == 1")
    fun getUserChallenges() : Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM ChallengeTable WHERE type == 2")
    fun getPopularChallenges() : Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM ChallengeTable WHERE type == 3")
    fun getMyChallenges() : Flow<List<ChallengeEntity>>


    @Query("DELETE FROM ChallengeTable")
    fun clearChallengeTable()
}