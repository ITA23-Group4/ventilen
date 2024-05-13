package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId
import com.google.type.DateTime

data class Event(
    val eventName: String = "",
    val attendeesByUID: MutableList<String> = mutableListOf(),
    val eventDateTime: DateTime = DateTime.getDefaultInstance(),
    val eventDescription: String = "",
    val eventAddress: String = "",
    val eventPrice: Double = 0.0,
    // val eventDateTime: DateTime = DateTime.getDefaultInstance(), TODO: Implement this
    @DocumentId val eventID: String,
) : Comparable<Event> {
    fun withUpdatedAttendees(attendees: MutableList<String>): Event {
        return this.copy(attendeesByUID = attendees)
    }

    /**
     * Compares this Event with another Event based on their names.
     *
     * @param other The other Event to compare with.
     * @return A negative integer if this name is less than [other]'s name,
     *         zero if they are equal, or a positive integer if this name is greater.
     * TODO: Look into Comparator if we want to sort in other ways than natural order
     */
    override fun compareTo(other: Event): Int {
        return eventName.compareTo(other.eventName)
    }

}
