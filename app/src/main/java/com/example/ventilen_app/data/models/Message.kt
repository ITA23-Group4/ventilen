package com.example.ventilen_app.data.models

import java.util.Date

/**
 * Data class representing a message.
 *
 * @property senderUID The unique identifier of the sender.
 * @property message The content of the message.
 * @property timestamp The date and time when the message was sent.
 * @property locationID The unique identifier of the location where the message was sent.
 * @property username The username of the sender.
 *
 * @constructor Creates a new instance of Message.
 *
 * @author Christian, Nikolaj, Marcus
 */
data class Message(
    val senderUID: String,
    val message: String,
    val timestamp: Date,
    val locationID: String,
    val username: String
)
