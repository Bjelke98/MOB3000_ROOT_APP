package com.example.mob3000_root_app.screens.admin.apiRequest

import com.squareup.moshi.Json

data class PasswordChange(
    @Json(name = "password") val password: String,
    @Json(name = "newPassword") val newPassword: String
)
