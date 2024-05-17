package com.example.ventilen_app

import com.example.ventilen_app.data.models.Location
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class LocationTest {
    private lateinit var locationA: Location
    private lateinit var locationB: Location
    private lateinit var locationC: Location

    @Before
    fun setUp() {
        locationA = Location(locationName = "Location A")
        locationB = Location(locationName = "Location B")
        locationC = Location(locationName = "Location C")
    }

    /**
     * Tests the compareTo function of the Location class.
     * Verifies that locations are compared based on their names.
     */
    @Test
    fun testCompareTo() {
        // Verify locationA is less than locationB
        assert(locationA < locationB)
        assertEquals(-1, locationA.compareTo(locationB))

        // Verify locationB is greater than locationA
        assert(locationB > locationA)
        assertEquals(1, locationB.compareTo(locationA))

        // Verify locationA is equal to itself
        assertEquals(0, locationA.compareTo(locationA))
    }

    /**
     * Tests sorting a list of Location objects.
     * Verifies that the locations are sorted alphabetically by their names.
     */
    @Test
    fun testLocationSorting() {
        val locations = listOf(locationB, locationA, locationC)

        val sortedLocations = locations.sorted()

        // Assert
        assertEquals("Location A", sortedLocations[0].locationName)
        assertEquals("Location B", sortedLocations[1].locationName)
        assertEquals("Location C", sortedLocations[2].locationName)
    }
}