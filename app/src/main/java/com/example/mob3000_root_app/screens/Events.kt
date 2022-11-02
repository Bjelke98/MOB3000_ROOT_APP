package com.example.mob3000_root_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.EventData
import com.example.mob3000_root_app.data.RootService
import kotlinx.coroutines.launch

class EventsModel : ViewModel(){
    var eventListResponse:List<EventData> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getEventList() {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val eventList = apiService.getEvents()
                eventListResponse = eventList
                Log.i("data", eventList.get(0).image)
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

@Composable
fun Events(eventList: List<EventData>) {
    Box(Modifier
        .background(MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(10.dp)) {

            LazyColumn(


                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        text = "Events"
                    )
                }

                items(items = eventList) { event ->
                    EventCard(event, ArticleType.VERTICAL_ARTICLE)
                }
            }
        }
    }
}