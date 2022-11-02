package com.example.mob3000_root_app.testData

import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.Comment
import com.example.mob3000_root_app.data.User

class ArticleTestdata {
    val list = listOf<Comment>()

    val comments = listOf<Comment>(
        Comment(1, "id_1","Hei du", "01/01/00", list , User("id1","Nils","Almaas")),
        Comment(2, "id_2","Heisan", "01/01/00", list , User("id2","Jonas","Patric")),
        Comment(3, "id_3","Stein", "01/01/00", list , User("id3","Hefalump","Petroliun")),
        Comment(4, "id_4","Argnif Muldrulag", "01/01/00", list , User("id4","Heldrun","Biderulder")),
    )
    var dataList: List<ArticleData> = listOf(
        ArticleData(1,"Title1", comments, "Gaming Mouse","https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp","01/01/00", "Title1"),
        ArticleData(2,"LinrikLink",comments,  "Gaming Mouse","https://linrik.herokuapp.com/api/resources/image-1654869276879.png", "01/01/00", "Title2"),
        ArticleData(3,"Title2", comments,  "Gaming Mouse","https://img-9gag-fun.9cache.com/photo/aKEPP3W_460swp.webp", "01/01/00", "Title3"),
        ArticleData(4,"Title3", comments, "Gaming Mouse","https://img-9gag-fun.9cache.com/photo/aKEPP93_460swp.webp","01/01/00", "Title4"),
        ArticleData(5,"Title4", comments, "Gaming Mouse","https://img-9gag-fun.9cache.com/photo/aDWEjK9_700bwp.webp","01/01/00" , "Title5"),
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