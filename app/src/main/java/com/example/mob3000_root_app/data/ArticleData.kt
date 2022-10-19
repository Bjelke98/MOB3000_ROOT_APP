package com.example.mob3000_root_app.data

data class ArticleData(val title: String, val image: String, val description: String, val _id: Int, val comments: List<Comment>)
enum class ArticleType{    VERTICAL_ARTICLE, HORIZONTAL_ARTICLE     }