package com.example.myfinalproject.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinalproject.R
import com.example.myfinalproject.models.Result
import com.example.myfinalproject.ui.theme.GlideImage
import com.example.myfinalproject.viewmodels.MovieViewModel
import com.example.myfinalproject.viewmodels.Page
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Result, onBack: () -> Unit,onMovieClick: (Result) -> Unit, movieViewModel: MovieViewModel = viewModel(), modifier: Modifier) {
    LaunchedEffect(movie.id) {
        movieViewModel.fetchCredits(movie.id)
        movieViewModel.fetchRecommendMovies(movie.id)
    }
    val context = LocalContext.current
    val castList by movieViewModel.creditMovies.collectAsState()
    val recommended by movieViewModel.recommendedMovies.collectAsState()

    // State to manage bottom sheet visibility
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    BackHandler {
        onBack()
    }
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                GlideImage(
                    url = "https://image.tmdb.org/t/p/w500/${movie.backdrop_path}",
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
//                        .clickable {
//                            val youtubeUrl =
//                                "https://www.youtube.com/results?search_query=${Uri.encode(movie.title)} trailer"
//                            val youtubeAppIntent =
//                                Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
//                                    setPackage("com.google.android.youtube")
//                                }
//                            val intentChooser = Intent.createChooser(youtubeAppIntent, "Open with")
//                            context.startActivity(intentChooser)
//                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 20f
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(350.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        ElevatedCard(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(130.dp)
                                .height(180.dp)
                                .background(Color.Transparent)
                        ) {
                            Box{
                                GlideImage(
                                    url = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                    contentDescription = movie.title,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White,
                                modifier = Modifier
                            )
                            Text(
                                text = movie.release_date,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Row(modifier=Modifier.align(Alignment.CenterHorizontally)){
                                Icon(painter = painterResource(R.drawable.baseline_star_24),
                                    contentDescription = "Star",
                                    tint = Color.Red)
                                Text(
                                    text = String.format("%.1f",movie.vote_average),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color.Red,
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(painter = painterResource(R.drawable.baseline_people_24),
                                    contentDescription = "Star",)
                                Text(
                                    text = formatVoteCount(movie.vote_count),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color.White,
                                    modifier = Modifier
                                )
                            }
                            Row(modifier=Modifier.align(Alignment.CenterHorizontally)) {
                                Icon(painter = painterResource(R.drawable.baseline_videocam_24),
                                    contentDescription ="trailer",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(55.dp)
                                        .clickable {
                                            val youtubeUrl =
                                                "https://www.youtube.com/results?search_query=${
                                                    Uri.encode(
                                                        movie.title
                                                    )
                                                } trailer"
                                            val youtubeAppIntent =
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(youtubeUrl)
                                                ).apply {
                                                    setPackage("com.google.android.youtube")
                                                }
                                            val intentChooser =
                                                Intent.createChooser(youtubeAppIntent, "Open with")
                                            context.startActivity(intentChooser)
                                        }
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(painter = painterResource(id = R.drawable.baseline_share_24),
                                    contentDescription = "Share",
                                    modifier = Modifier
                                        .padding(0.dp,6.dp)
                                        .height(40.dp)
                                        .width(40.dp)
                                        .clickable {
                                            scope.launch {
                                                showBottomSheet = true
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Cast",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (castList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(castList) { cast ->
                        DetailScreenCastList(cast = cast)
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(26.dp))
        }
        item{

            Text(
                text = "Recommendations",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
            LazyRow {
                items(recommended) { movie->
                    DetailScreenListItem(movie = movie, onClick = {
                        movieViewModel.selectMovie(movie)
                        movieViewModel.setPage(Page.TAB)
                    })
                }
            }
        }
    }
    // Bottom sheet content for sharing
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet=false
            },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier
                .padding(0.dp,0.dp,0.dp,70.dp)
            ) {
                Text(
                    text = "Wanna share this movie!!!!!!!!!!!!!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row (modifier = Modifier
                    .align(Alignment.End)){

                Button(onClick = {

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Check out this movie: ${movie.title}")
                        type = "text/plain"
                    }
                    context.startActivity(shareIntent)
                    showBottomSheet=false
                }) {
                    Text("Share")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Cancel")
                }
            }
            }
        }
    }
}
fun formatVoteCount(voteCount: Int): String {
    return when {
        voteCount >= 1_000_000 -> String.format("%.1fM", voteCount / 1_000_000.0)
        voteCount >= 1_000 -> String.format("%.1fK", voteCount / 1_000.0)
        else -> voteCount.toString()
    }
}



