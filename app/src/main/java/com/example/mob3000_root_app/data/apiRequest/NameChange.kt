package com.example.mob3000_root_app.data.apiRequest

import com.squareup.moshi.Json

data class NameChange(
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String
)