package com.example.myfinalproject.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.viewmodels.MovieViewModel
import com.example.myfinalproject.viewmodels.Page

@Composable
fun CategoryScreen(category: String, movieViewModel: MovieViewModel, onBack: () -> Unit,modifier: Modifier) {
    val movies by movieViewModel.getMoviesByCategory(category).collectAsState()

    BackHandler {
        onBack()
    }

    LaunchedEffect(category) {
        movieViewModel.fetchMoviesByCategory(category)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = category,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(movies) { movie ->
                CategoryScreenListItem(movie = movie, onClick = {
                    movieViewModel.selectMovie(movie)
                    movieViewModel.setPage(Page.TAB)
                })
            }
        }
    }
}

