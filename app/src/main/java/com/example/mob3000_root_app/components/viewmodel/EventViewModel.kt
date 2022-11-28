package com.example.mob3000_root_app.components.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.*
import com.example.mob3000_root_app.data.apiResponse.EventData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import com.example.mob3000_root_app.data.apiResponse.emptyEventData
import com.example.mob3000_root_app.data.apiRequest.CommentData
import com.example.mob3000_root_app.data.apiRequest.EventId
import com.example.mob3000_root_app.data.apiResponse.Comment
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    var eventListResponse = mutableStateListOf<EventData>()
    var eventByIDResponse by mutableStateOf(emptyEventData)

    var joinedEvents = mutableListOf<EventData>()

    var errorMessage: String by mutableStateOf("")
    var focusedEvent by mutableStateOf(emptyEventData)
        private set

    fun getEventList() {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val eventDataInput = apiService.getEvents()
                if(eventDataInput != eventListResponse){
                    eventListResponse.clear()
                    eventListResponse.addAll(eventDataInput)
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun postComment(eventID: String, text: String, cb:(ResponseStatus?) -> Unit) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val postedStatus = apiService.postComment(
                    "event",
                    CommentData(eventID, text)
                )
                cb.invoke( postedStatus )
                Log.i("CommentStatus", postedStatus.toString())
            } catch (e: Exception) {
                cb.invoke( null )
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun joinEvent(eventID: String){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                val joinResponse = apiService.joinEvent(EventId(eventID))
                Log.i("Join API Call: ", joinResponse.toString())
            }
            catch (e: Exception){
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun leaveEvent(eventID: String){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                val leaveResponse = apiService.leaveEvent(EventId(eventID))
                Log.i("leave API Call: ", leaveResponse.toString())
            }
            catch (e: Exception){
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun getJoinedEvents(cb: () -> Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                joinedEvents = apiService.getJoinedEvents() as MutableList<EventData>
                Log.i("Join API Call: ", joinedEvents.toString())
                cb.invoke()
            }
            catch (e: Exception){
                Log.i("Catch Joined", e.message.toString())
                cb.invoke()
            }
        }
    }

    fun getEventByID(eventId: String, cb: (Boolean) -> Unit){

        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                eventByIDResponse = apiService.getEventByID(eventId)

                Log.i("Try: Event API Call", "before Invoke")
                cb.invoke(true)
                Log.i("Try: Event API Call", eventByIDResponse.toString())
            }
            catch (e: Exception){
                cb.invoke(false)
                Log.i("Catch: EventByID", e.message.toString())
            }
        }
    }

    fun deleteEventById(eventid: String, cb: (status: ResponseStatus?)->Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val responseStatus = apiService.deleteEventById(EventId(eventid))
                Log.i("adminLog", "event ble slettet")
                cb.invoke(responseStatus)
            } catch (e: Exception){
                Log.i("Catch: DeleteByID", e.message.toString())
                cb.invoke(null)
            }
        }
    }

    fun focusEvent(event: EventData){
        focusedEvent = event
    }

    fun focusEventByID(eventID: String, cb: (Boolean) -> Unit){
        getEventByID(eventID){ response ->
            if(response) {
                focusedEvent = eventByIDResponse
                cb.invoke(true)
            }
            else
                cb.invoke(false)
        }
    }

    fun prepFullEvent(event: EventData, cb: () -> Unit){
        focusEvent(event)
        getEventList()
        getJoinedEvents(){
            cb.invoke()
        }
    }
}