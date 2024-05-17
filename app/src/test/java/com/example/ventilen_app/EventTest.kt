package com.example.ventilen_app

import com.example.ventilen_app.data.models.Event
import com.google.firebase.Timestamp
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventTest {

    private lateinit var eventA: Event
    private lateinit var eventB: Event
    private lateinit var eventC: Event

    @Before
    fun setUp() {
        eventA = Event(
            eventName = "Event A",
            eventDateTime = Timestamp(1620000000, 0),
            eventID = "1"
        )
        eventB = Event(
            eventName = "Event B",
            eventDateTime = Timestamp(1630000000, 0),
            eventID = "2"
        )
        eventC = Event(
            eventName = "Event C",
            eventDateTime = Timestamp(1640000000, 0),
            eventID = "3"
        )
    }

    /**
     * Tests the compareTo function of the Event class.
     * Verifies that events are compared based on their date and time.
     */
    @Test
    fun testCompareTo() {
        assert(eventA < eventB)
        assert(eventB < eventC)
        assertEquals(0, eventA.compareTo(eventA))
    }

    /**
     * Tests sorting a list of Event objects.
     * Verifies that the events are sorted chronologically by their date and time.
     */
    @Test
    fun testEventSorting() {
        val events = listOf(eventB, eventA, eventC) // not in order

        val sortedEvents = events.sorted()

        // Assert
        assertEquals("Event A", sortedEvents[0].eventName)
        assertEquals("Event B", sortedEvents[1].eventName)
        assertEquals("Event C", sortedEvents[2].eventName)
    }
}