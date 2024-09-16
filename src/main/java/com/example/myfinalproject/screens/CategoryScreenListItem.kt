package com.example.myfinalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.models.Result
import com.example.myfinalproject.ui.theme.GlideImage

@Composable
fun CategoryScreenListItem(movie: Result, onClick: (Result) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxWidth()
            .height(600.dp)
            .clickable { onClick(movie) }
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            GlideImage(
                url = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}