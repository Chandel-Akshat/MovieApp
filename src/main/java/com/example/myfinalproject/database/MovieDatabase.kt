package com.example.myfinalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}