package com.example.ventilen_app.data

import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    val db = Firebase.firestore;

    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await()
            .toObject(User::class.java)
    }

}