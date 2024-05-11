package com.example.ventilen_app.data.models

data class Message(
    val senderUID: String,
    val message: String,
    val timestamp: Long,
    val locationID: String,
    val username: String
)

