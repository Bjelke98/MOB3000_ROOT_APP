package com.example.mob3000_root_app.data.apiResponse

data class ArticleData(
    val __v: Int,
    val _id: String,
    var comments: List<Comment>,
    val description: String,
    val image: String?,
    val postedAt: String,
    val title: String
)

val emptyArticleData = ArticleData(0,"", listOf(),"","","","")