package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

data class Location(
    val name: String,
    @DocumentId var uid: String? = null
) : Comparable<Location> {

    /**
     * Compares this Location with another Location based on their names.
     *
     * @param other The other Location to compare with.
     * @return A negative integer if this name is less than [other]'s name,
     *         zero if they are equal, or a positive integer if this name is greater.
     */
    override fun compareTo(other: Location): Int {
        return name.compareTo(other.name)
    }
}