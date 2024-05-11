package com.example.ventilen_app.data.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.DocumentId

data class Event(
    val title: String = "",
    val attendeesByUID: MutableList<String> = mutableListOf(),
    @DocumentId val id: String,
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
     * @author Marcus
     *
     * TODO: Look into Comparator if we want to sort in other ways than natural order
     */
    override fun compareTo(other: Event): Int {
        return title.compareTo(other.title)
    }

}
