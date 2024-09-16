package com.example.myfinalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerScreen(onCategorySelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxHeight()
        .width(170.dp)
        .background(Color.Black)) {
        // Existing code for DrawerContent
        Text(
            text = "Now Playing",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onCategorySelected("Now Playing") }
        )
        Text(
            text = "Top Rated",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onCategorySelected("Top Rated") }
        )
        Text(
            text = "Popular",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onCategorySelected("Popular") }
        )
        Text(
            text = "Upcoming",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onCategorySelected("Upcoming") }
        )
    }
}

