package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.User
import com.example.ventilen_app.services.AccountService
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService();
    private val repository: Repository = Repository()

    var username: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var location: String by mutableStateOf("")
    var password: String by mutableStateOf("Ventilen1234")



    fun registerNewUser() {
        viewModelScope.launch {
            try {
                accountService.createUserWithEmailAndPassword(
                    email = email,
                    password = password,
                    username = username
                )?.let { newUser ->
                    repository.createUser(newUser)
                }
            } catch (error: Exception) {
                Log.d("CREATE_USER", "Failed to create user: $error")

            }
        }

        // This is the original code that was replaced by the code above
        // Changed the code to make use of coroutines instead of callbacks

        /*
        accountService.authenticate(
            email = email,
            password = password,
            username = username,
            onRegistrationSuccess = { newUser ->
                repository.createUser(
                    newUser,
                    onRegistrationSuccess,
                    onRegistrationFailed
                )
            },
            onRegistrationFailed = onRegistrationFailed
        )
         */
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                accountService.login(
                    email = email,
                    password = password
                )
            } catch (error: Exception) {
                Log.d("Logged In", "Failed to log in: $error")
            }

        }
    }

    /*
    fun loginUser(navigateOnLoginSuccess: () -> Unit, onLoginFailed: () -> Unit) {
        accountService.login(
            email = email,
            password = password,
            navigateOnLoginSuccess = navigateOnLoginSuccess,
            onLoginFailed = onLoginFailed
        )
    }
    */
}