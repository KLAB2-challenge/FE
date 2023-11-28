package com.example.klab2challenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MCPTable") //Home Challenge Preview
data class MCPEntity(
    val number : Int,
    val title : String,
    val image : String,
    val participant : Int,
    val progress : Double
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}