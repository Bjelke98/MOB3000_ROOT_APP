package com.example.mob3000_root_app.data.apiResponse

data class Comment(
    val __v: Int,
    val _id: String,
    val comment: String,
    val postedAt: String,
    val reply: List<Comment>,
    val user: User
)