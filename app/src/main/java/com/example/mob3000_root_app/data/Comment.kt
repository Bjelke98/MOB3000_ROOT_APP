package com.example.mob3000_root_app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//data class Comment(val user: User, val comment: String) {
@Parcelize
data class Comment(
    val __v: Int,
    val _id: String,
    val comment: String,
    val postedAt: String,
    val reply: List<String>,
    val user: User
): Parcelable