package com.example.ventilen_app.data.models

import java.util.Date

data class Message(
    val senderUID: String,
    val message: String,
    val timestamp: Date,
    val locationID: String,
    val username: String
)

