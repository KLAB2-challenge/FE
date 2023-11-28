package com.example.klab2challenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HCPTable") //Home Challenge Preview
data class HCPEntity(
    val number : Int,
    val title : String,
    val image : String,
    val participant : Int,
    val duration : String,
    val frequency : String,
    val progress : Double,
    val type : Int //0-official, 1-user, 2-popular
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}