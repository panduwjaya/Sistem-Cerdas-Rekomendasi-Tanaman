package com.example.sistemcerdasrekomendasitanaman.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], exportSchema = false, version = 1)
abstract class FavoriteRoom : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteRoom? = null
        fun getInstance(context: Context): FavoriteRoom =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoom::class.java, "Favorite_db"
                ).build()
            }

    }
}
