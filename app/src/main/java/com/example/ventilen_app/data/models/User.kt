package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

data class User(
    val username: String = "",
    val primaryLocationID: String = "",
    @DocumentId var uid: String? = null
) {
    override fun toString(): String {
        return "User(username='$username', uid=$uid)"
    }

}