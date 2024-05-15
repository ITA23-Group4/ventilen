package com.example.ventilen_app.services

import android.util.Log
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AccountService {
    private val auth = FirebaseAuth.getInstance()

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        username: String,
        location: Location
    ): User {
        val firebaseAuthResult = auth
            .createUserWithEmailAndPassword(email, password)
            .await()
        val authUIDForDocumentTitle: String = firebaseAuthResult.user?.uid!!
        Log.d("CREATE_USER", "User created: $authUIDForDocumentTitle")
        return User(
            username = username,
            primaryLocationID = location.locationID!!,
            uid = authUIDForDocumentTitle
        )
    }

    suspend fun login(
        email: String,
        password: String
    ) {
        auth.signInWithEmailAndPassword(
            email,
            password
        ).await()
        Log.d("Logged In", "Logged in!")
    }

}
