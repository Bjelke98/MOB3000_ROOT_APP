package com.example.mob3000_root_app.screens.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.ArticleType

@Composable
fun Events(
    appVM: AppViewModel
) {
    val eventVM = appVM.eventVM
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Column(Modifier.padding(10.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = eventVM.eventListResponse){ event ->
                    EventCard(
                        event = event,
                        type = ArticleType.VERTICAL_ARTICLE,
                        appVM
                    )
                }
            }
        }
    }
}
