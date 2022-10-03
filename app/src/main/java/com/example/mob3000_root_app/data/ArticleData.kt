package com.example.mob3000_root_app.data

data class ArticleData(val title: String, val image: Int, val imageDescription: String)
enum class ArticleType(){
    VERTICAL_ARTICLE, HORIZONTAL_ARTICLE
}