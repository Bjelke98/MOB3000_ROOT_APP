package com.example.mob3000_root_app.screens

import android.util.Log
import androidx.compose.foundation.background
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
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.data.EventData
import com.example.mob3000_root_app.data.EventItem
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
//    val eventList = listOf(
//        EventItem("Feb",5,"Hackathon", R.drawable.summer),
//        EventItem("May",17,"17. mai frokost",R.drawable.summer),
//        EventItem("Jun",5,"Summerfestivals", R.drawable.testing),
//        EventItem("Oct",5,"MOB3000 Presentasjon", R.drawable.testing),
//    )

    LazyColumn(modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceVariant)) {
        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "Events")
        }

        items(items = eventList) {
                event -> EventCard(event)
        }
    }
}