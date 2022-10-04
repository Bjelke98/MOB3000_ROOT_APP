package com.example.mob3000_root_app.data

import com.example.mob3000_root_app.R

class ArticleTestdata {
    val dataList: List<ArticleData> = listOf(
        ArticleData("Title1", R.drawable.gaming_mouse, "Gaming Mouse"),
        ArticleData("Title2", R.drawable.opinion_wrong, "You Wrong"),
        ArticleData("Title3", R.drawable.wow, "Owen Wilson"),
        ArticleData("Title4", R.drawable.sauce, "OhoDog"),
    )

    val loremIpsum: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
}

