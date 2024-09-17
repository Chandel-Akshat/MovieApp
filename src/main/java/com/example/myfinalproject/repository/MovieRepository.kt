package com.example.myfinalproject.repository

import com.example.myfinalproject.api.MovieNAPI
import com.example.myfinalproject.database.FavoriteMovie
import com.example.myfinalproject.database.FavoriteMovieDao
import com.example.myfinalproject.models.Cast
import com.example.myfinalproject.models.Credit
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.myfinalproject.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieNAPI: MovieNAPI, private val favoriteMovieDao: FavoriteMovieDao) {

    private val _nowplaying = MutableStateFlow<List<Result>>(emptyList())
    val nowPlayingMovies:StateFlow<List<Result>>
        get()= _nowplaying
    suspend fun fetchNowPlayingMovies(apiKey:String, page:Int){
        val response = movieNAPI.getNowPlayingMovies(apiKey,page)
        if(response.isSuccessful&&response.body()!=null){
            _nowplaying.value = (response.body()!!.results)
        }
    }


    private val _topRatedMovies = MutableStateFlow<List<Result>>(emptyList())
    val topRatedMovies: StateFlow<List<Result>> get() = _topRatedMovies
    suspend fun fetchTopRatedMovies(apiKey: String, page: Int) {
        val response = movieNAPI.getTopRatedMovies(apiKey, page)
        if (response.isSuccessful && response.body() != null) {
            _topRatedMovies.value = response.body()!!.results
        }
    }

    private val _popularMovies = MutableStateFlow<List<Result>>(emptyList())
    val popularMovies:StateFlow<List<Result>> get() = _popularMovies
    suspend fun fetchPopularMovies(apiKey: String, page: Int){
        val reponse = movieNAPI.getPopularMovies(apiKey,page)
        if(reponse.isSuccessful && reponse.body()!=null){
            _popularMovies.value = reponse.body()!!.results
        }
    }

    private val _upcomingMovies = MutableStateFlow<List<Result>>(emptyList())
    val upcomingMovies:StateFlow<List<Result>> get() = _upcomingMovies
    suspend fun fetchUpcomingMovies(apiKey: String,page: Int){
        val response = movieNAPI.getUpcomingMovies(apiKey,page)
        if(response.isSuccessful && response.body()!=null){
            _upcomingMovies.value = response.body()!!.results
        }
    }

    private val _creditMovies = MutableStateFlow<List<Cast>>(emptyList())
    val creditMovies:StateFlow<List<Cast>> get() = _creditMovies
    suspend fun fetchCreditMovies(id: Int,apiKey: String){
        val response = movieNAPI.getCreditMovies(id,apiKey)
        if(response.isSuccessful && response.body()!=null){
            _creditMovies.value = response.body()!!.cast
        }
    }

    private val _recommendMovies = MutableStateFlow<List<Result>>(emptyList())
    val recommendMovies:StateFlow<List<Result>>get() = _recommendMovies
    suspend fun fetchRecommendMovies(id:Int,apiKey: String){
        val response = movieNAPI.getRecommendationsMovies(id,apiKey)
        if(response.isSuccessful && response.body()!=null){
            _recommendMovies.value = response.body()!!.results
        }
    }


    suspend fun addMovieToFavorites(movie: FavoriteMovie) {
        favoriteMovieDao.insert(movie)
    }

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return favoriteMovieDao.getAllFavoriteMovies()
    }

    suspend fun deleteFavoriteMovie(id: Int) {
        favoriteMovieDao.deleteById(id)
    }


}