package com.example.w1833600

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val movieId:Int,
    val title:String,
    val year:Int,
    val rating:String,
    val released:String,
    val runtime:String,
    val genre:String,
    val director:String,
    val writer:String,
    val actors:String,
    val plot:String
)