package com.example.ventilen_app.services

import android.util.Log
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

/**
 * Service class for handling user account operations such as creating and logging in users.
 *
 * @property auth The instance of FirebaseAuth used for authentication operations.
 *
 * @constructor Creates an instance of AccountService.
 *
 * @Author [Your Name] TODO: Add name
 */
class AccountService {
    private val auth = FirebaseAuth.getInstance()

    /**
     * Creates a new user with the specified email, password, username, and location.
     *
     * @param email The email address of the new user.
     * @param password The password for the new user.
     * @param username The username of the new user.
     * @param location The primary location of the new user.
     * @return The created User object.
     * @throws Exception If there is an error during user creation.
     */
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

    /**
     * Logs in a user with the specified email and password.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @throws Exception If there is an error during login.
     */
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
