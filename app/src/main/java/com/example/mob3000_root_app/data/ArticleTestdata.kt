package com.example.mob3000_root_app.data

import org.json.JSONObject

class ArticleTestdata {
    val comments = listOf<Comment>(
        Comment(User("Jonas","Almaas"),"Hei du"),
        Comment(User("Nils","Patric"),"Heisan"),
        Comment(User("Hefalump","Petroliun"),"Stein")
    )
    val dataList: List<ArticleData> = listOf(
        ArticleData("Title1", "https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp", "Gaming Mouse",1,comments),
        ArticleData("LinrikLink", "https://linrik.herokuapp.com/api/resources/image-1654869276879.png", "Gaming Mouse", 2, comments),
        ArticleData("Title2", "https://img-9gag-fun.9cache.com/photo/aKEPP3W_460swp.webp", "Gaming Mouse", 3, comments),
        ArticleData("Title3", "https://img-9gag-fun.9cache.com/photo/aKEPP93_460swp.webp", "Gaming Mouse",4, comments),
        ArticleData("Title4", "https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp", "Gaming Mouse",5 , comments),
    )

//    val jsonDataList: List<ArticleJsonData> = listOf(
////        ArticleJsonData(JSONObject("'title':'Title1', 'image':gaming_mouse','imageDescription':'Gaming Mouse"))
//        ArticleJsonData(JSONObject().put("title","Title1"))
//
//    )

//    val jason = buildJsonObject()
    val loremIpsum: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
        " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
        " when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
        " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
        " when an unknown printer took a galley of type and scrambled it to make a type specimen book."
}