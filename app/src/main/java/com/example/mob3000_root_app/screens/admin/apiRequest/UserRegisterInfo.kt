package com.example.mob3000_root_app.screens.admin.apiRequest

import com.squareup.moshi.Json

data class  UserRegisterInfo(
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
)
