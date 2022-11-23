package com.example.mob3000_root_app.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.cards.AdminEventCard
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventAdmin (
    appVM: AppViewModel,
) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    appVM.ppEventVM.newEvent()
                    navigateUpTo(navController = appVM.navController, Screen.EditEvent)
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, "New event")
            }
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(items = appVM.eventVM.eventListResponse) { event ->
                    AdminEventCard(
                        eventData = event,
                        appVM = appVM,
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }
}