package com.example.mob3000_root_app.data

import org.json.JSONObject

class EventTestdata {
    val dataList: List<EventData> = listOf(
        EventData("BokseKamp","10.10.10",200,"https://media.istockphoto.com/photos/woman-boxer-punching-a-punching-bag-picture-id1129844432?k=20&m=1129844432&s=612x612&w=0&h=u8FOZRbVQ5R1jiDije_Ng1Nfc2d8tuOLNYnzXvax7NU=","Dame")
    )

    val jsonDataList: List<ArticleJsonData> = listOf(
//        ArticleJsonData(JSONObject("'title':'Title1', 'image':gaming_mouse','imageDescription':'Gaming Mouse"))
        ArticleJsonData(JSONObject().put("title","Title1"))

    )

    //    val jason = buildJsonObject()
    val loremIpsum: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
            " when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
            " when an unknown printer took a galley of type and scrambled it to make a type specimen book."
}