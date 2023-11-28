package com.example.klab2challenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rankingTable") //Home Challenge Preview
data class RankingEntity(
    val userName : String,
    val image : String,
    val currentBorder : Int,
    val totalCoin : Int,
    val ranking : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}