package com.example.klab2challenge.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable") //Home Challenge Preview
data class UserEntity(
    var name : String,
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