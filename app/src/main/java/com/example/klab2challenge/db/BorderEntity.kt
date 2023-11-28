package com.example.klab2challenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BorderTable") //Home Challenge Preview
data class BorderEntity(
    val number : Int,
    val color : Int,
    val name : String,
    val price : Int,
    var isLocked : Boolean,
    var isChcked : Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}