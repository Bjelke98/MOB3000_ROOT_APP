package com.example.mob3000_root_app.data.apiRequest

import com.squareup.moshi.Json

data class  UserLoginInfo(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)
