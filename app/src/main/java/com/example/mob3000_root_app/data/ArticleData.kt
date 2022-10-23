package com.example.mob3000_root_app.data

//data class ArticleData(val title: String, val image: String, val description: String, val _id: Int, val comments: List<Comment>)
data class ArticleData(
    val __v: Int,
    val _id: String,
    val comments: List<Comment>,
    val description: String,
    val image: String,
    val postedAt: String,
    val title: String
)

enum class ArticleType{    VERTICAL_ARTICLE, HORIZONTAL_ARTICLE     }