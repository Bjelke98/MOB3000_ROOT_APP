package com.example.mob3000_root_app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val _id: String,
    val firstname: String,
    val lastname: String
) : Parcelable