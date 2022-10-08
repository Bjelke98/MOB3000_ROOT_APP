package com.example.mob3000_root_app.data

import org.json.JSONObject

data class ArticleData(val title: String, val image: String, val imageDescription: String)
enum class ArticleType{
    VERTICAL_ARTICLE, HORIZONTAL_ARTICLE
}

data class ArticleJsonData(val json: JSONObject)