package com.example.myfinalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinalproject.R
import com.example.myfinalproject.models.Cast
import com.example.myfinalproject.models.Result
import com.example.myfinalproject.ui.theme.GlideImage
import com.example.myfinalproject.viewmodels.MovieViewModel
import com.example.myfinalproject.viewmodels.Page

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayoutExample(
    movie: Result,
    onBack: () -> Unit,
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Overview", "Cast", "Similar Movies", "Similar Movies", "Similar Movies", "Similar Movies")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = movie.title ?: "Movie Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {

            ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, tabTitle ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = tabTitle) }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    MovieOverview(movie)
                }
                1 -> {
                    MovieCast(movie.id)
                }
                2 -> {
                    SimilarMovies(movie.id)
                }
            }
        }
    }
}

@Composable
fun MovieOverview(movie: Result) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Overview", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.overview)
    }
}

@Composable
fun MovieCast(movieId: Int,movieViewModel: MovieViewModel = viewModel()) {

    LaunchedEffect(movieId) {
        movieViewModel.fetchCredits(movieId)
    }


    val castList by movieViewModel.creditMovies.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cast", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))


        if (castList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(castList) { cast ->
                    CastListItem(cast = cast)
                }
            }
        }
    }
}

@Composable
fun CastListItem(cast: Cast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        GlideImage(
            url = "https://image.tmdb.org/t/p/w500/${cast.profile_path}",
            contentDescription = cast.name,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(text = cast.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Character: ${cast.character}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Popularity: ${cast.popularity}", style = MaterialTheme.typography.bodySmall)
        }
    }

}

@Composable
fun SimilarMovies(movieId: Int,movieViewModel: MovieViewModel= viewModel()) {
    LaunchedEffect(movieId) {
        movieViewModel.fetchRecommendMovies(movieId)
    }
    val similarMovieList by movieViewModel.recommendedMovies.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Similar Movies", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))


        if (similarMovieList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(similarMovieList) { movie ->
                    similarMovieListItem(movie = movie, onClick = {
                        movieViewModel.selectMovie(movie)
                        movieViewModel.setPage(Page.DETAIL)
                    })
                }
            }
        }
    }
}

@Composable
fun similarMovieListItem(movie: Result,onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(8.dp)
                .width(130.dp)
                .height(180.dp)
                .clickable { onClick() }
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GlideImage(
                    url = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 6
            )
            Row{
                Icon(painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Star",
                    tint = Color.Red)
                Text(
                    text = String.format("%.1f",movie.vote_average),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(painter = painterResource(R.drawable.baseline_people_24),
                    contentDescription = "Star",)
                Text(
                    text = formatVoteCount(movie.vote_count),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier
                )
            }
        }
    }
}


