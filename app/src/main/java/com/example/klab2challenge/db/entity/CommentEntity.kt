package com.example.klab2challenge.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CommentTable") //Home Challenge Preview
data class CommentEntity(
    val userName : String,
    val userCurrentBorder : Int,
    val userImage : String,
    val content : String,
    val date : String,
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}