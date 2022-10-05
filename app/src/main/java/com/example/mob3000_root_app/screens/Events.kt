package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.data.EventItem

@Composable
fun Events() {
    val eventList = listOf(
        EventItem("Feb",5,"Hackathon", R.drawable.summer),
        EventItem("May",17,"17. mai frokost",R.drawable.summer),
        EventItem("Jun",5,"Summerfestivals", R.drawable.testing),
        EventItem("Oct",5,"MOB3000 Presentasjon", R.drawable.testing),
    )

    LazyColumn() {
        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "Events")
        }

        items(items = eventList) {
                item -> EventCard(item)
        }
    }
}