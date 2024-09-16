package com.example.myfinalproject.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinalproject.viewmodels.MovieViewModel
import com.example.myfinalproject.viewmodels.Page
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier

@Composable
fun MovieApp(movieViewModel: MovieViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentPage by movieViewModel.currentPage.collectAsState()
    val selectedCategory by movieViewModel.selectedCategory.collectAsState()
    val nowPlayingState by movieViewModel.nowPlayingMovies.collectAsState()
    val topRatedState by movieViewModel.topRatedMovies.collectAsState()
    val popularState by movieViewModel.popularMovies.collectAsState()
    val upcomingState by movieViewModel.upcomingMovies.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchMovies()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerScreen(onCategorySelected = { category ->
                movieViewModel.setCategory(category)
                movieViewModel.setPage(Page.CATEGORY)
                scope.launch { drawerState.close() }
            })
        },
        content = {
            when (currentPage) {
                Page.HOME -> {

                    HomeScreen(
                        nowPlayingState = nowPlayingState,
                        topRatedState = topRatedState,
                        popularState = popularState,
                        upcomingState = upcomingState,
                        onMovieClick = { movie ->
                            movieViewModel.selectMovie(movie)
                            movieViewModel.setPage(Page.DETAIL)
                        },
                        onDrawerOpen = { scope.launch { drawerState.open() } },
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()

                    )
                }
                Page.DETAIL -> {
                    movieViewModel.selectedMovie.value?.let {
                        DetailScreen(
                            movie = it,
                            onBack = {
                                movieViewModel.setPage(Page.HOME)
                            },
                            onMovieClick = { movie ->
                                movieViewModel.selectMovie(movie)
                                movieViewModel.setPage(Page.DETAIL)
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .systemBarsPadding()
                        )
                    }
                }
                Page.CATEGORY -> {
                    selectedCategory?.let {
                        CategoryScreen(
                            category = it,
                            movieViewModel = movieViewModel,
                            onBack = {
                                movieViewModel.setPage(Page.HOME)
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .systemBarsPadding()
                        )
                    }
                }
                Page.TAB ->{
                    movieViewModel.selectedMovie.value?.let {
                        TabLayoutExample(
                            movie = it,
                            onBack = {
                                movieViewModel.setPage(Page.HOME)
                            },
//                            onMovieClick = { movie ->
//                                movieViewModel.selectMovie(movie)
//                                movieViewModel.setPage(Page.DETAIL)
//                            },
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .systemBarsPadding()
                        )
                    }
                }
            }
        }
    )
}








