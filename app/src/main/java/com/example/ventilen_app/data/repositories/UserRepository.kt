package com.example.ventilen_app.data.repositories

import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore;

    suspend fun isEmailInAdmins(email: String): Boolean {
        val querySnapshot = db.collection("admins")
            .whereEqualTo("email", email)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }

    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await()
            .toObject(User::class.java)
    }


    // New createUser function that uses coroutines
    suspend fun createUser(newUser: User) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(newUser)
            .await()
    }
}