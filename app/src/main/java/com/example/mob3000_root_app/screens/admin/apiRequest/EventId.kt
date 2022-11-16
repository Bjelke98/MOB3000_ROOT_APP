package com.example.mob3000_root_app.screens.admin.apiRequest

import com.squareup.moshi.Json

data class EventId(
    @Json(name = "eventid") val eventid: String
)
