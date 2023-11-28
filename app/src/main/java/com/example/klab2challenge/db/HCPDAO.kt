package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HCPDAO {
    @Insert
    fun addHCP(hcp : HCPEntity)

    @Update
    fun updateHCP(hcp : HCPEntity)

    @Delete
    fun deleteHCP(hcp : HCPEntity)

    @Query("SELECT * FROM HCPTable WHERE type == 0")
    fun getOfficialHCPs() : List<HCPEntity>

    @Query("SELECT * FROM HCPTable WHERE type == 1")
    fun getUserHCPs() : List<HCPEntity>

    @Query("SELECT * FROM HCPTable WHERE type == 2")
    fun getPopularHCPs() : List<HCPEntity>

    @Query("DELETE FROM HCPTable")
    fun clearHCPTable()
}