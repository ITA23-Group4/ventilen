package com.example.ventilen_app.services

import android.util.Log
import com.example.ventilen_app.data.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AccountService {
    private val auth = FirebaseAuth.getInstance()


    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        username: String
    ): User? {
        return try {
            val firebaseAuthResult = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            val authUIDForDocumentTitle: String = firebaseAuthResult.user?.uid!!
            Log.d("CREATE_USER", "User created: $authUIDForDocumentTitle")
            User(username, authUIDForDocumentTitle)
        } catch (error: Exception) {
            Log.d("CREATE_USER", "Failed to create user: $error")
            null
        }
    }


    fun authenticate(
        email: String,
        password: String,
        username: String,
        onRegistrationSuccess: (User) -> Unit,
        onRegistrationFailed: () -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { firebaseAuthResult ->
                val newAuthUID: String = firebaseAuthResult.user?.uid!!
                val newUser: User = User(username, newAuthUID)
                onRegistrationSuccess(newUser)
            }
            .addOnFailureListener {
                onRegistrationFailed()
            }
    }

    suspend fun login(
        email: String,
        password: String
    ) {
        try {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            Log.d("Logged In", "Logged in!")
        } catch (error: Exception) {
            Log.d("Logged In", "Failed to log in: $error")
        }

    }

    /*

    fun login(
        email: String,
        password: String,
        navigateOnLoginSuccess: () -> Unit,
        onLoginFailed: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("Logged In", "Logged in!")
                navigateOnLoginSuccess()
            }
            .addOnFailureListener {
                onLoginFailed()
            }
    }

    */
}
