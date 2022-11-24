package com.example.mob3000_root_app.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R

@Composable
fun DefaultAsyncImage(image: String, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://linrik.herokuapp.com/api/resources/$image")
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.testing),
        contentDescription = ("Image could not load"),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}