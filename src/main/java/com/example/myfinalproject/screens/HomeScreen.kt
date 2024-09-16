package com.example.myfinalproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.models.Result

@Composable
fun HomeScreen(
    nowPlayingState: List<Result>,
    topRatedState: List<Result>,
    popularState: List<Result>,
    upcomingState: List<Result>,
    onMovieClick: (Result) -> Unit,
    onDrawerOpen: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = Modifier.fillMaxSize().padding(5.dp,20.dp,0.dp,0.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
        ) {
            IconButton(onClick = { onDrawerOpen() }) {
                Icon(Icons.Filled.Menu, contentDescription = "Open Drawer")
            }
            Text(
                text = "Movie App",
                style = MaterialTheme.typography.headlineLarge
            )

        }
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }

            val categories = listOf(
                "Now Playing Movies" to nowPlayingState,
                "Top Rated Movies" to topRatedState,
                "Popular Movies" to popularState,
                "Upcoming Movies" to upcomingState,
                "Now Playing Movies" to nowPlayingState,
                "Top Rated Movies" to topRatedState,
                "Popular Movies" to popularState,
                "Upcoming Movies" to upcomingState,
                "Now Playing Movies" to nowPlayingState,
                "Top Rated Movies" to topRatedState,
                "Popular Movies" to popularState,
                "Upcoming Movies" to upcomingState,
                "Now Playing Movies" to nowPlayingState,
                "Top Rated Movies" to topRatedState,
                "Popular Movies" to popularState,
                "Upcoming Movies" to upcomingState,
            )

            categories.forEach { (category, data) ->
                item {
                    Text(text = category, style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(data) { movie ->
                            HomeScreenListItem(movie = movie, onClick = onMovieClick)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}


