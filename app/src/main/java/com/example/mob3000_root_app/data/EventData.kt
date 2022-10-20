package com.example.mob3000_root_app.data

import org.json.JSONObject

data class EventData(val title: String, val date: String, val attendants:Int, val image: String, val imageDescription: String)
enum class EventType{
    VERTICAL_Event, HORIZONTAL_Event
}

data class EventJsonData(val json: JSONObject)