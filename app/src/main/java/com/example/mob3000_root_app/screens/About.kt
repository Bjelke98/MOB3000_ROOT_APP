package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.cards.AboutCard
import com.example.mob3000_root_app.components.cards.AboutFAQCARD
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.data.AboutFAQ

@Composable
fun About() {
    val aboutList = listOf(
        AboutData("Adrian", "Leader", "Han er flink"),
        AboutData("Henrik", "Leader", "Han liker Kroa"),
        AboutData("Olav", "Nestleder", "Hun liker Java"),
        AboutData("John Ivar", "Sjef", "Hun liker Java"),
        AboutData("Krister", "2x times", "Hun liker Java"),
        AboutData("Herman", "Høy kar", "Hun liker Java"),
        )

    val aboutFaqList = listOf(
        AboutFAQ("Hvem er Root?","En Linjeforening"),
        AboutFAQ("Hvordan får jeg en A?","Jobbe med MOB3000")
    )

    LazyColumn() {

        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "About us") }

        items(items = aboutList ) {
                item -> AboutCard(item)
        }

        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "FAQ") }

        items(items = aboutFaqList) {
                item -> AboutFAQCARD(item)
        }
    }

}