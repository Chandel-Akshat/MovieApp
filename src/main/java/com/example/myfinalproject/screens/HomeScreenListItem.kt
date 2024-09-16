package com.example.myfinalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.models.Result
import com.example.myfinalproject.ui.theme.GlideImage
import com.example.myfinalproject.viewmodels.MovieViewModel
import com.example.myfinalproject.viewmodels.Page

@Composable
fun HomeScreenListItem(movie: Result, onClick: (Result) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .width(130.dp)
            .height(180.dp)
            .clickable { onClick(movie) }
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable { onClick(movie) }
        ) {
            GlideImage(
                url = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
