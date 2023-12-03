package com.example.klab2challenge.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.klab2challenge.db.entity.RecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDAO {
    @Insert
    fun addRecord(record : RecordEntity)

    @Query("UPDATE RecordTable SET commentNum = :num WHERE id = :recordId")
    fun updateRecordCommentNum(recordId : Int, num: Int)

    @Delete
    fun deleteRecord(record : RecordEntity)

    @Query("SELECT * FROM RecordTable")
    fun getRecords() : Flow<List<RecordEntity>>

    @Query("DELETE FROM RecordTable")
    fun clearRecordTable()
}