package com.example.mob3000_root_app.screens.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.*
import com.example.mob3000_root_app.data.apiResponse.EventData

@Composable
fun Events(
    navController: NavHostController,
    eventViewModel: EventViewModel,
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Column(Modifier.padding(10.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = eventViewModel.eventListResponse){ event ->
                    EventCard(

                        navController,
                        event = event, ArticleType.HORIZONTAL_ARTICLE,
                        { eventViewModel.focusEvent(event) }
                    )
                }
            }
        }
    }
}
