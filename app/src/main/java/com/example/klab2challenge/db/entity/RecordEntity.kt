package com.example.klab2challenge.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecordTable") //Home Challenge Preview
data class RecordEntity(
    val number : Int,
    val userName : String,
    val userCurrentBorder : Int,
    val userImage : String,
    val title : String,
    val description : String,
    val image : String,
    val date : String,
    val commentNum : Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}