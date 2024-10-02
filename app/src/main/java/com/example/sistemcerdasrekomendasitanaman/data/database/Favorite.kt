package com.example.sistemcerdasrekomendasitanaman.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_plant")
data class Favorite(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUri: Int
)