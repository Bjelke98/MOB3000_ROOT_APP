package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.buttons.EndreArtikkel
import com.example.mob3000_root_app.components.buttons.SlettArtikkel

@Composable
fun EventAdmin (navController: NavHostController) {
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