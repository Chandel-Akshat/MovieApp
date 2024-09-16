package com.example.myfinalproject.api

import com.example.myfinalproject.models.Credit
import com.example.myfinalproject.models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNAPI {

    @GET(value = "movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<Movie>

    @GET(value = "movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<Movie>

    @GET(value = "movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<Movie>

    @GET(value = "movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")apiKey:String,@Query("page")page:Int):Response<Movie>

    @GET(value = "movie/{id}/credits")
    suspend fun getCreditMovies(@Path("id")id:Int,@Query("api_key")apiKey: String):Response<Credit>

    @GET(value = "movie/{id}/recommendations")
    suspend fun getRecommendationsMovies(@Path("id")id:Int,@Query("api_key")apiKey: String):Response<Movie>
}
