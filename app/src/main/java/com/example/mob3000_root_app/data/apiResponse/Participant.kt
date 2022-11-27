package com.example.mob3000_root_app.data.apiResponse

import com.squareup.moshi.Json

data class Participant(
    @Json(name = "_id") val _id: String,
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String
)