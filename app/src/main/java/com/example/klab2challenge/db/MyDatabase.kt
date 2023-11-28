package com.example.klab2challenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserEntity::class, ChallengeEntity::class, RankingEntity::class, BorderEntity::class], version = 3)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getHCPDAO() : ChallengeDAO
    abstract fun getUserDAO() : UserDAO
    abstract fun getBorderDAO() : BorderDAO
    abstract fun getRankingDAO() : RankingDAO

    private class MyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(database: MyDatabase) {

        }
    }

    companion object {
        var instance : MyDatabase? = null

        fun getInstance(context: Context, scope : CoroutineScope) : MyDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "challenge-database"
                )   .build()
            }

            return instance!!
        }
    }
}