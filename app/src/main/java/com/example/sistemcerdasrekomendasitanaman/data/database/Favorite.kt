package com.example.sistemcerdasrekomendasitanaman.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_plant")
@Parcelize
data class Favorite(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUri: Int
) : Parcelable