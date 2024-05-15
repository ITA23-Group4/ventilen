package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.PasswordValidationState
import com.example.ventilen_app.data.models.ValidatePassword
import com.example.ventilen_app.data.repositories.UserRepository
import com.example.ventilen_app.services.AccountService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService();
    private val userRepository: UserRepository = UserRepository()
    private val validatePassword: ValidatePassword = ValidatePassword()


    var username: String by mutableStateOf("")

    var email: String by mutableStateOf("")

    var location: Location by mutableStateOf(Location(
        locationName = "Name",
        latestMessage = "Latest message",
        abbreviation = "Abbreviation",
        locationID = "Location ID"
        )
    )

    var password: String by mutableStateOf("")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _passwordError: StateFlow<PasswordValidationState> =
        snapshotFlow { password }
            .mapLatest { validatePassword.execute(password) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PasswordValidationState()
            )

    val passwordError: StateFlow<PasswordValidationState> = _passwordError

    fun changePassword(value: String) {
        password = value
    }



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
                    userRepository.createUser(newUser)
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