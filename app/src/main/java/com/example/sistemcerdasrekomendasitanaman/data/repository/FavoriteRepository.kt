package com.example.sistemcerdasrekomendasitanaman.data.repository

import androidx.lifecycle.LiveData
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.data.database.FavoriteDao

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    fun getAllFavorites(): LiveData<List<Favorite>> = favoriteDao.getAllFavorites()

    suspend fun addFavorite(plant: Favorite) {
        favoriteDao.insertFavorite(plant)
    }

    suspend fun removeFavorite(plant: Favorite) {
        favoriteDao.removeFavorite(plant)
    }

    suspend fun isFavorite(plantId: Int): Boolean {
        return favoriteDao.getFavoriteById(plantId) != null
    }
}