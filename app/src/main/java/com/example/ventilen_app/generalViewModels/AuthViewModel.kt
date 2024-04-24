package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.User
import com.example.ventilen_app.services.AccountService

class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService();
    private val repository: Repository = Repository()

    var username: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var location: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    fun registerNewUser(
        onRegistrationSuccess: () -> Unit,
        onRegistrationFailed: () -> Unit
    ) {
        accountService.authenticate(
            email = email,
            password = password,
            username = username,
            onRegistrationSuccess = { newUser ->
                repository.createUser(newUser, onRegistrationSuccess, onRegistrationFailed)
            },
            onRegistrationFailed = onRegistrationFailed
        )
    }

    fun loginUser(navigateOnLoginSuccess: () -> Unit, onLoginFailed: () -> Unit) {
        accountService.login(email, password, navigateOnLoginSuccess, onLoginFailed)
    }

}