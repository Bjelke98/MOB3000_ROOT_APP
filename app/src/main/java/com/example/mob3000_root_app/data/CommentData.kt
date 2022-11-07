package com.example.mob3000_root_app.data

import com.squareup.moshi.Json

data class CommentData(
    @Json(name = "postid") val postid: String,
    @Json(name = "comment") val text: String
)
