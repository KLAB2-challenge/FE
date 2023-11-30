package com.example.klab2challenge.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChallengeTable") //Home Challenge Preview
data class ChallengeEntity(
    val number : Int,
    val title : String,
    val image : String,
    val participant : Int,
    val duration : String,
    val frequency : String,
    val progress : Double,
    val isJoin : Boolean,
    val type : Int //0-official, 1-user, 2-popular, 3-my
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}