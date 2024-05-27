package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
data class Event(
    val eventName: String = "",
    val attendeesByUID: MutableList<String> = mutableListOf(),
    val eventStartDateTime: Date = Date(),
    val eventEndDateTime: Date = Date(),
    val eventDescription: String = "",
    val eventAddress: String = "",
    val eventPrice: Double = 0.0,
    @DocumentId val eventID: String = "",
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
     */
    override fun compareTo(other: Event): Int {
        return eventStartDateTime.compareTo(other.eventStartDateTime) ?: 0
    }

    fun getDate(): String {
        return getDateWithTimeRange().split(" 20").first()
    }

    fun getDateWithTimeRange(): String {
        val dateFormat = SimpleDateFormat("dd. MMM yyyy\n'Klokken' HH:mm", Locale("da", "DK"))
        val startDate = eventStartDateTime
        val formattedStartDate = dateFormat.format(startDate)

        val endDate = eventEndDateTime
        val formattedEndDateTime = SimpleDateFormat("HH:mm", Locale("da", "DK")).format(endDate)

        val monthIndex = formattedStartDate.indexOf(' ') + 1 // Index of the character after the whitespace
        val capitalizedMonthDate = formattedStartDate.replaceRange(monthIndex, monthIndex + 1, formattedStartDate[monthIndex].uppercase())

        return "$capitalizedMonthDate - $formattedEndDateTime"
    }
}
