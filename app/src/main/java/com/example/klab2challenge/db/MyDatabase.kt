package com.example.klab2challenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.klab2challenge.db.dao.BorderDAO
import com.example.klab2challenge.db.dao.ChallengeDAO
import com.example.klab2challenge.db.dao.RankingDAO
import com.example.klab2challenge.db.dao.RecordDAO
import com.example.klab2challenge.db.dao.UserDAO
import com.example.klab2challenge.db.entity.BorderEntity
import com.example.klab2challenge.db.entity.ChallengeEntity
import com.example.klab2challenge.db.entity.RankingEntity
import com.example.klab2challenge.db.entity.RecordEntity
import com.example.klab2challenge.db.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserEntity::class, ChallengeEntity::class, RankingEntity::class, BorderEntity::class, RecordEntity::class], version = 10)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getChallengeDAO() : ChallengeDAO
    abstract fun getUserDAO() : UserDAO
    abstract fun getBorderDAO() : BorderDAO
    abstract fun getRankingDAO() : RankingDAO
    abstract fun getRecordDAO() : RecordDAO

    class MyDatabaseCallback(
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
            database.getChallengeDAO().clearChallengeTable()
            database.getBorderDAO().clearBorderTable()
            database.getRankingDAO().clearRankingTable()
            database.getUserDAO().clearUserTable()
            database.getRecordDAO().clearRecordTable()
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
                ).fallbackToDestructiveMigration()
                    .addCallback(MyDatabaseCallback(scope))
                    .build()
            }

            return instance!!
        }
    }
}