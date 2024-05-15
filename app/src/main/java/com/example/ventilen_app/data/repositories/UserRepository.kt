package com.example.ventilen_app.data.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore;
    var currentUser: User? by mutableStateOf(null)

    suspend fun getUser() {
        val currentUserUID: String = FirebaseAuth.getInstance().currentUser!!.uid

        currentUser = db.collection("users").document(currentUserUID).get().await()
            .toObject(User::class.java)

        Log.d("CurrentUser:", "Username = ${currentUser?.username.toString()} primaryLocation = ${currentUser?.primaryLocationID.toString()} UID = ${currentUser?.uid.toString()}")
    }

    // New createUser function that uses coroutines
    suspend fun createUser(newUser: User) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(newUser)
            .await()
    }
}