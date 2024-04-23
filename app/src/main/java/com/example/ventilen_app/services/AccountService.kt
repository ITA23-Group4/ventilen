package com.example.ventilen_app.services

import android.util.Log
import com.example.ventilen_app.data.models.User
import com.google.firebase.auth.FirebaseAuth

class AccountService {
    private val auth = FirebaseAuth.getInstance()

    fun authenticate(
        email: String,
        password: String,
        username: String,
        onAuthSuccess: (User) -> Unit,
        onAuthFailed: () -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { firebaseAuthResult ->
                val newAuthUID: String = firebaseAuthResult.user?.uid!!
                val newUser: User = User(username, newAuthUID)
                onAuthSuccess(newUser)
            }
            .addOnFailureListener {
                onAuthFailed()
            }
    }

    fun login(
        email: String,
        password: String,
        navigateOnLoginSuccess: () -> Unit,
        navigateOnLoginFailed: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUserEmail = auth.currentUser?.email
                Log.d("CURRENT EMAIL", "login: $currentUserEmail")
                navigateOnLoginSuccess()
            }
            .addOnFailureListener {
                navigateOnLoginFailed()
            }
    }
}
