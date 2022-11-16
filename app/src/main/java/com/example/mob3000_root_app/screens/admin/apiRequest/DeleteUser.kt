package com.example.mob3000_root_app.screens.admin.apiRequest

import com.squareup.moshi.Json

data class DeleteUser(
    @Json(name = "password") val password: String
)
