package com.example.mob3000_root_app.data.apiRequest

import com.squareup.moshi.Json

data class DeleteUser(
    @Json(name = "password") val password: String
)
