package com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.data.database.FavoriteRoom
import com.example.sistemcerdasrekomendasitanaman.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavoriteRepository

    init {
        val favoriteDao = FavoriteRoom.getInstance(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
    }

    val allFavorites: LiveData<List<Favorite>> = repository.getAllFavorites()

    fun addFavorite(plant: Favorite) = viewModelScope.launch {
        repository.addFavorite(plant)
    }

    fun removeFavorite(plant: Favorite) = viewModelScope.launch {
        repository.removeFavorite(plant)
    }

    suspend fun isFavorite(plantId: Int): Boolean = repository.isFavorite(plantId)
}