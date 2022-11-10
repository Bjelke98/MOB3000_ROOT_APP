package com.example.mob3000_root_app.components.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.*
import com.example.mob3000_root_app.data.apiRequest.CommentData
import com.example.mob3000_root_app.data.apiRequest.EventId
import com.example.mob3000_root_app.data.apiResponse.EventData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import com.example.mob3000_root_app.data.apiResponse.RootService
import com.example.mob3000_root_app.data.apiResponse.emptyEventData
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    val apiService = RootService.getInstance()
    var eventListResponse: List<EventData> by mutableStateOf(listOf())
    var eventByIDResponse by mutableStateOf(emptyEventData)
    var errorMessage: String by mutableStateOf("")
    var focusedEvent by mutableStateOf(emptyEventData)
        private set
    val postedStatus: ResponseStatus by mutableStateOf(ResponseStatus(0))

    fun getEventList() {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val eventList = apiService.getEvents()
                eventListResponse = eventList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun postComment(eventID: String, text: String) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val postedStatus = apiService.postComment(
                    "event",
                    CommentData(eventID, text)
                )
                Log.i("CommentStatus", postedStatus.toString())
            } catch (e: Exception) {
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun getEventByID(eventId: String){

        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                eventByIDResponse = apiService.getEventByID(eventId)
                Log.i("Try: Event API Call", eventByIDResponse.toString())
            }
            catch (e: Exception){
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun deleteEventById(eventid: String){
        viewModelScope.launch {
            try {
                var data = apiService.deleteEventById(EventId(eventid))
                Log.i("adminLog", "event ble slettet")
            } catch (e: Exception){
                Log.i("Error", e.message.toString())
            }
        }
    }

    fun focusEvent(focusEvent: EventData){
        focusedEvent = focusEvent
    }
}