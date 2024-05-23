package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

/**
 * Data class representing a user.
 *
 * @property username The username of the user.
 * @property primaryLocationID The unique identifier of the user's primary location.
 * @property isAdmin A flag indicating whether the user has administrative privileges.
 * @property uid The unique identifier for the user, annotated with @DocumentId for Firestore integration.
 *
 * @constructor Creates a new instance of User.
 *
 * @author Christian, Nikolaj, Marcus
 */
data class User(
    val username: String = "",
    val primaryLocationID: String = "",
    var isAdmin: Boolean = false,
    @DocumentId var uid: String? = null
) {
    /**
     * Returns a string representation of the User.
     *
     * @return A string containing the username, uid, and admin status of the user.
     */
    override fun toString(): String {
        return "User(username='$username', uid=$uid, isAdmin=$isAdmin)"
    }
}
