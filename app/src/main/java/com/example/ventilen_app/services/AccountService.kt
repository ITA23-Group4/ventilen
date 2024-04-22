package com.example.ventilen_app.services

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AccountService {
    fun authenticate(email: String, password: String, onResult: () -> Unit, onFail: () -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener {
                onFail()
            }
    }

    fun login(email: String, password: String, onResult: () -> Unit, onFail: () -> Unit): Unit {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUserEmail = Firebase.auth.currentUser?.email
                Log.d("CURRENT EMAIL", "login: $currentUserEmail")
                onResult()
            }
            .addOnFailureListener {
                onFail()
            }
    }
}