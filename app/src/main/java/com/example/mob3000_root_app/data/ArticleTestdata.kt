package com.example.mob3000_root_app.data

import org.json.JSONObject

class ArticleTestdata {
    val dataList: List<ArticleData> = listOf(
        ArticleData("Title1", "https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp", "Gaming Mouse"),
        ArticleData("VideoTitle", "https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp", "Gaming Mouse"),
        ArticleData("Title2", "https://img-9gag-fun.9cache.com/photo/aKEPP3W_460swp.webp", "Gaming Mouse"),
        ArticleData("Title3", "https://img-9gag-fun.9cache.com/photo/aKEPP93_460swp.webp", "Gaming Mouse"),
        ArticleData("Title4", "https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp", "Gaming Mouse"),
    )

    val jsonDataList: List<ArticleJsonData> = listOf(
//        ArticleJsonData(JSONObject("'title':'Title1', 'image':gaming_mouse','imageDescription':'Gaming Mouse"))
        ArticleJsonData(JSONObject().put("title","Title1"))

    )

//    val jason = buildJsonObject()
    val loremIpsum: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
}

