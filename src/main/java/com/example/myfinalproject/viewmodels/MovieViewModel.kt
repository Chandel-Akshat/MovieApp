package com.example.myfinalproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinalproject.database.FavoriteMovie
import com.example.myfinalproject.models.Cast
import com.example.myfinalproject.models.Result
import com.example.myfinalproject.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _nowPlayingMovies = MutableStateFlow<List<Result>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Result>> = _nowPlayingMovies

    private val _topRatedMovies = MutableStateFlow<List<Result>>(emptyList())
    val topRatedMovies: StateFlow<List<Result>> = _topRatedMovies

    private val _popularMovies = MutableStateFlow<List<Result>>(emptyList())
    val popularMovies: StateFlow<List<Result>> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<List<Result>>(emptyList())
    val upcomingMovies: StateFlow<List<Result>> = _upcomingMovies

    private val _currentPage = MutableStateFlow(Page.HOME)
    val currentPage: StateFlow<Page> = _currentPage

    private val _selectedMovie = MutableStateFlow<Result?>(null)
    val selectedMovie: StateFlow<Result?> = _selectedMovie

    private val _creditMovies = MutableStateFlow<List<Cast>>(emptyList())
    val creditMovies: StateFlow<List<Cast>> = _creditMovies

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    private val _recommendMovies = MutableStateFlow<List<Result>>(emptyList())
    val recommendedMovies :StateFlow<List<Result>> = _recommendMovies

    init {
        fetchMovies()
    }
    fun fetchMovies() {
        viewModelScope.launch {
            fetchNowPlayingMovies()
            fetchTopRatedMovies()
            fetchPopularMovies()
            fetchUpcomingMovies()
        }
    }

    fun getMoviesByCategory(category: String): StateFlow<List<Result>> {
        return when (category) {
            "Now Playing" -> nowPlayingMovies
            "Top Rated" -> topRatedMovies
            "Popular" -> popularMovies
            "Upcoming" -> upcomingMovies
            else -> MutableStateFlow(emptyList())
        }
    }

    fun fetchMoviesByCategory(category: String) {
        viewModelScope.launch {
            when (category) {
                "Now Playing" -> fetchNowPlayingMovies()
                "Top Rated" -> fetchTopRatedMovies()
                "Popular" -> fetchPopularMovies()
                "Upcoming" -> fetchUpcomingMovies()
            }
        }
    }

    fun setPage(page: Page) {
        _currentPage.value = page
    }

    fun selectMovie(movie: Result) {
        _selectedMovie.value = movie
    }

    fun fetchCredits(id: Int) {
        viewModelScope.launch {
            repository.fetchCreditMovies(id, "90ab00085c2a5816e9588e38f01e392a")
            _creditMovies.value = repository.creditMovies.value
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            repository.fetchTopRatedMovies("90ab00085c2a5816e9588e38f01e392a", 1)
            _topRatedMovies.value = repository.topRatedMovies.value
        }
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            repository.fetchNowPlayingMovies("90ab00085c2a5816e9588e38f01e392a", 1)
            _nowPlayingMovies.value = repository.nowPlayingMovies.value
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            repository.fetchPopularMovies("90ab00085c2a5816e9588e38f01e392a", 1)
            _popularMovies.value = repository.popularMovies.value
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            repository.fetchUpcomingMovies("90ab00085c2a5816e9588e38f01e392a", 1)
            _upcomingMovies.value = repository.upcomingMovies.value
        }
    }

    fun fetchRecommendMovies(id:Int){
        viewModelScope.launch {
            repository.fetchRecommendMovies(id,"90ab00085c2a5816e9588e38f01e392a")
            _recommendMovies.value = repository.recommendMovies.value
        }
    }
    fun setCategory(category: String) {
        _selectedCategory.value = category
    }
    fun addToFavorites(movie: Result) {
        viewModelScope.launch {
            val favoriteMovie = FavoriteMovie(
                id = movie.id,
                title = movie.title,
                posterPath = movie.poster_path,
                overview = movie.overview
            )
            repository.addMovieToFavorites(favoriteMovie)
        }
    }

    fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return repository.getAllFavoriteMovies()
    }

    fun removeFavoriteMovie(id: Int) {
        viewModelScope.launch {
            repository.deleteFavoriteMovie(id)
        }
    }
}

enum class Page {
    HOME,
    DETAIL,
    CATEGORY,
    TAB
}

