package com.example.mob3000_root_app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//data class ArticleData(val title: String, val image: String, val description: String, val _id: Int, val comments: List<Comment>)
@Parcelize
data class ArticleData(
    val __v: Int,
    val _id: String,
    val comments: List<Comment>,
    val description: String,
    val image: String,
    val postedAt: String,
    val title: String
): Parcelable