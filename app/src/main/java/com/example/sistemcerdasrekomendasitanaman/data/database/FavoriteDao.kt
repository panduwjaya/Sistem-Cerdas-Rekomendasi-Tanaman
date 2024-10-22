package com.example.sistemcerdasrekomendasitanaman.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(plant: Favorite)

    @Delete
    suspend fun removeFavorite(plant: Favorite)

    @Query("SELECT * FROM favorite_plant WHERE id = :plantId LIMIT 1")
    suspend fun getFavoriteById(plantId: Int): Favorite?

    @Query("SELECT * FROM favorite_plant")
    fun getAllFavorites(): LiveData<List<Favorite>>
}