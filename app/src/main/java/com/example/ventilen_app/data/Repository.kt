package com.example.ventilen_app.data

import android.util.Log
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

    fun createUser(newUser: User) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(newUser)
            .addOnSuccessListener {
                Log.d("CREATE_USER", "User created: $newUser")
            }
            .addOnFailureListener {
                Log.d("CREATE_USER", "Failed to create user: $newUser")
            }
    }

}