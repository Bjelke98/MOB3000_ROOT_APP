package com.example.mob3000_root_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp


import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.buttons.EndreArtikkel
import com.example.mob3000_root_app.components.buttons.SlettArtikkel
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.EventData


@Composable
fun AdminEventCard(navController: NavHostController, data: EventData) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        colors = testColors
    ) {

        Row(
            Modifier
                .padding(5.dp)
                .align(Alignment.End)){
            SlettArtikkel()
            EndreArtikkel()
        }
    }
}