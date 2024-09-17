package com.example.myfinalproject.di

import android.content.Context
import androidx.room.Room
import com.example.myfinalproject.database.FavoriteMovieDao
import com.example.myfinalproject.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(db: MovieDatabase): FavoriteMovieDao {
        return db.favoriteMovieDao()
    }
}