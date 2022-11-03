package com.example.mob3000_root_app.data

data class EventData(
    val __v: Int,
    val _id: String,
    val comments: List<Any>,
    val dateFrom: String,
    val dateTo: String,
    val address: String?,
    val description: String,
    val image: String,
    val participants: List<Participant>,
    val postedAt: String,
    val title: String
)