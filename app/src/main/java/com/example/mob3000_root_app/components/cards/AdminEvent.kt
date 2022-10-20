package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.EventItem

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
import com.example.mob3000_root_app.data.EventType

@Composable
fun AdminEventCard(navController: NavHostController, data: EventData, type: EventType) {
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