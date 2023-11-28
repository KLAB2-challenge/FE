package com.example.klab2challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MCPDAO {
    @Insert
    fun addMCP(mcp : MCPEntity)

    @Update
    fun updateMCP(mcp : MCPEntity)

    @Delete
    fun deleteMCP(mcp : MCPEntity)

    @Query("SELECT * FROM MCPTable")
    fun getMCPs() : List<MCPEntity>

    @Query("DELETE FROM MCPTable")
    fun clearMCPTable()
}