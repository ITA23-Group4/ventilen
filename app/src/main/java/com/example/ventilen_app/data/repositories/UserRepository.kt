package com.example.ventilen_app.data.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
object UserRepository {
    private val db = Firebase.firestore
    var currentUser: User? by mutableStateOf(null)

    suspend fun getUser() {
        val currentUserFirebaseInstance: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val currentUserUID: String = currentUserFirebaseInstance.uid

        // Get the user from Firestore and convert to User class
        val userDocumentRef = db.collection("users").document(currentUserUID).get().await()
        currentUser = convertUserDocumentToUser(userDocumentRef)

        Log.d(
            "CurrentUser:",
            "Username = ${currentUser?.username.toString()} primaryLocation = ${currentUser?.primaryLocationID.toString()} isAdmin=${currentUser?.isAdmin} UID = ${currentUser?.uid.toString()}"
        )
    }

    // New createUser function that uses coroutines
    suspend fun createUser(newUser: User) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(
                hashMapOf(
                    "username" to newUser.username,
                    "primaryLocationID" to newUser.primaryLocationID,
                    "isAdmin" to newUser.isAdmin,
                )
            )
            .await()
    }

    private fun convertUserDocumentToUser(document: DocumentSnapshot): User {
        val name: String = document.getString("username") ?: ""
        val primaryLocationID: String = document.getString("primaryLocationID") ?: ""
        val isAdmin: Boolean = document.getBoolean("isAdmin") ?: false
        val uid = document.id
        return User(
            username = name,
            primaryLocationID = primaryLocationID,
            isAdmin = isAdmin,
            uid = uid
        )
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

}