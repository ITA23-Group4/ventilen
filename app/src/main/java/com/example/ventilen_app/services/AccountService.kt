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
}
