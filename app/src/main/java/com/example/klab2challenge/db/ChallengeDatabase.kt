package com.example.klab2challenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, HCPEntity::class, MCPEntity::class, RankingEntity::class, BorderEntity::class], version = 3)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract fun getHCPDAO() : HCPDAO
    abstract fun getMCPDAO() : MCPDAO
    abstract fun getUserDAO() : UserDAO
    abstract fun getBorderDAO() : BorderDAO
    abstract fun getRankingDAO() : RankingDAO


    companion object {
        var instance : ChallengeDatabase? = null

        fun getInstance(context: Context) : ChallengeDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ChallengeDatabase::class.java,
                    "challenge-database"
                ).build()
            }

            return instance!!
        }
    }
}