//package com.example.myfinalproject.screens
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.myfinalproject.models.Result
//
//
///*
//@Preview(showBackground = true)
//@Composable
//fun PreviewMovieHomeScreen() {
//    val sampleNowPlayingMovies = listOf(
//        Result(
//            adult = false,
//            backdrop_path = "/sampleBackdrop1.jpg",
//            genre_ids = listOf(28, 12),
//            id = 12345,
//            original_language = "en",
//            original_title = "Sample Movie 1",
//            overview = "This is a sample now-playing movie overview.",
//            popularity = 50.0,
//            poster_path = "/samplePoster1.jpg",
//            release_date = "2023-01-01",
//            title = "Sample Movie 1",
//            video = false,
//            vote_average = 8.0,
//            vote_count = 100
//        )
//    )
//
//    val sampleTopRatedMovies = listOf(
//        Result(
//            adult = false,
//            backdrop_path = "/sampleBackdrop2.jpg",
//            genre_ids = listOf(28, 12),
//            id = 67890,
//            original_language = "en",
//            original_title = "Sample Top Rated Movie",
//            overview = "This is a sample top-rated movie overview.",
//            popularity = 60.0,
//            poster_path = "/samplePoster2.jpg",
//            release_date = "2023-02-01",
//            title = "Sample Top Rated Movie",
//            video = false,
//            vote_average = 9.0,
//            vote_count = 200
//        )
//    )
//
//    HomeContent(
//        nowPlayingData = sampleNowPlayingMovies,
//        topRatedData = sampleTopRatedMovies,
//        popularData = sampleTopRatedMovies,
//        upcomingData = sampleTopRatedMovies,
//        onClick = {},
//        onDraawerOpen={},
//    )
//}
//
// */
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMovieListItem() {
//    // Sample data
//    val sampleMovie = Result(
//        adult = false,
//        backdrop_path = "/sampleBackdrop.jpg",
//        genre_ids = listOf(28, 12),
//        id = 12345,
//        original_language = "en",
//        original_title = "Sample Movie",
//        overview = "This is a sample movie overview.",
//        popularity = 50.0,
//        poster_path = "/samplePoster.jpg",
//        release_date = "2023-01-01",
//        title = "Sample Movie",
//        video = false,
//        vote_average = 8.0,
//        vote_count = 100
//    )
//
//    HomeScreenListItem(
//        movie = sampleMovie,
//        onClick = {}
//    )
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMovieDetailScreen() {
//    val sampleMovie = Result(
//        adult = false,
//        backdrop_path = "/sampleBackdrop.jpg",
//        genre_ids = listOf(28, 12),
//        id = 12345,
//        original_language = "en",
//        original_title = "Sample Movie",
//        overview = "This is a sample movie overview.",
//        popularity = 50.0,
//        poster_path = "/samplePoster.jpg",
//        release_date = "2023-01-01",
//        title = "Sample Movie",
//        video = false,
//        vote_average = 8.0,
//        vote_count = 100
//    )
//
//    DetailScreen(
//        movie = sampleMovie,
//        onBack = {}, onMovieClick = {}, modifier = Modifier
//    )
//}