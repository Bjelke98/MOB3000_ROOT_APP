package com.example.mob3000_root_app.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.cards.AdminEventCard
import com.example.mob3000_root_app.components.viewmodel.AppViewModel


@Composable
fun EventAdmin (
    appVM: AppViewModel,
) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    Surface() {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Text(
                text = "Event Admin",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(items = appVM.eventVM.eventListResponse) { event ->
                    AdminEventCard(
                        eventData = event,
                        appVM = appVM,
                        editFocus = {appVM.ppEventVM.focusEvent(event)}
                    )
                }
            }
        }
    }
}