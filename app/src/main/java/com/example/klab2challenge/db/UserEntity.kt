package com.example.klab2challenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable") //Home Challenge Preview
data class UserEntity(
    var userName : String,
    var image : String,
    var currentBorder : Int,
    var ownBorders : String,
    var totalCoin : Int,
    var currentCoin : Int,
    var ranking : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}