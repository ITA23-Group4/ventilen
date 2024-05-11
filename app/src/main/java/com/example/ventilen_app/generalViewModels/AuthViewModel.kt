package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.User
import com.example.ventilen_app.services.AccountService
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService();
    private val repository: Repository = Repository()

    var username: String by mutableStateOf("")
    var email: String by mutableStateOf("christianbt96@gmail.com")
    var location: Location by mutableStateOf(Location("Name","UID"))
    var password: String by mutableStateOf("Ventilen1234")



    fun registerNewUser(
        onRegistrationSuccess: () -> Unit,
        onRegistrationFailed: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                accountService.createUserWithEmailAndPassword(
                    email = email,
                    password = password,
                    username = username,
                    location = location
                ).let { newUser ->
                    repository.createUser(newUser)
                    onRegistrationSuccess()
                }
            } catch (error: Exception) {
                Log.d("CREATE_USER", "Failed to create user: $error")
                onRegistrationFailed()
            }
        }
    }

    fun loginUser(
        onLoginSuccess: () -> Unit,
        onLoginFailure: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                accountService.login(
                    email = email,
                    password = password
                )
                onLoginSuccess()
            } catch (error: Exception) {
                Log.d("Logged In", "Failed to log in: $error")
                onLoginFailure()
            }

        }
    }

}