package com.example.mob3000_root_app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ArticleData(
    val __v: Int,
    val _id: String,
    val comments: List<Comment>,
    val description: String,
    val image: String,
    val postedAt: String,
    val title: String
)

val emptyArticleData = ArticleData(0,"", listOf(),"","","","")