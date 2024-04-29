package com.example.ventilen_app.data.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.DocumentId

data class Event(
    val title:String = "",
    val attendeesByUID: List<String> = listOf(),
    @DocumentId var id: String? = null
) {
    // fun withUpdatedAttendees(attendees: MutableList<String>): Event {
    //     return this.copy(attendeesByUID = attendees)
    // }
}
