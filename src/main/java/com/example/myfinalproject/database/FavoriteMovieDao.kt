package com.example.myfinalproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun insert(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteById(movieId: Int)

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId LIMIT 1")
    suspend fun getFavoriteMovieById(movieId: Int): FavoriteMovie?
}