package com.example.myfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myfinalproject.screens.MovieApp
import com.example.myfinalproject.ui.theme.MyFinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

                MyFinalProjectTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                            .statusBarsPadding(),
                        color = MaterialTheme.colorScheme.background

                    ) {
                        MovieApp()
                    }
                }
            }
        }
    }

