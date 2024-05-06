package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

data class Location(
    var name: String,
    @DocumentId var uid: String? = null
)