package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

data class Location(
    var locationName: String,
    var latestMessage: String,
    var abbreviation: String,
    @DocumentId var locationID: String? = null
) : Comparable<Location> {

    /**
     * Compares this Location with another Location based on their names.
     *
     * @param other The other Location to compare with.
     * @return A negative integer if this name is less than [other]'s name,
     *         zero if they are equal, or a positive integer if this name is greater.
     */
    override fun compareTo(other: Location): Int {
        return locationName.compareTo(other.locationName)
    }
}