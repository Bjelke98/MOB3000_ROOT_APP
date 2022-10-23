package com.example.mob3000_root_app.data

//data class Comment(val user: User, val comment: String) {
data class Comment(    val __v: Int,
                       val _id: String,
                       val comment: String,
                       val postedAt: String,
                       val reply: List<Any>,
                       val user: User
                   )