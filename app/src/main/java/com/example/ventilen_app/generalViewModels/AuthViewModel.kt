package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.utils.ValidateInput
import com.example.ventilen_app.data.repositories.UserRepository
import com.example.ventilen_app.services.AccountService
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()
    private val userRepository: UserRepository = UserRepository()
    private val validateInput: ValidateInput = ValidateInput()

    var isAdmin: Boolean? by mutableStateOf(null)

    var email: String by mutableStateOf("")
    var hasEmailError: Boolean by mutableStateOf(false)

    var password: String by mutableStateOf("")
    var passwordRepeat: String by mutableStateOf("")
    var hasPasswordError: Boolean by mutableStateOf(false)

    var username: String by mutableStateOf("")
    var hasUsernameError: Boolean by mutableStateOf(false)

    var location: Location by mutableStateOf(Location(
        locationName = "",
        latestMessage = "",
        abbreviation = "",
        locationID = ""
    )
    )
    var hasLocationError: Boolean by mutableStateOf(false)


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
                //isEmailInAdmins() TODO Should be changed
                onLoginSuccess()
            } catch (error: Exception) {
                Log.d("Logged In", "Failed to log in: $error")
                onLoginFailure()
            }

        }
    }

    private suspend fun isEmailInAdmins() {
        isAdmin = userRepository.isEmailInAdmins(email)
    }

    fun changeEmail(newEmail: String) {
        email = newEmail
        hasEmailError = validateInput.validateEmail(email)
    }

    fun changePassword(newPassword: String) {
        password = newPassword
        hasPasswordError = validateInput.validatePassword(password)
    }

    fun changeRepeatedPassword(newRepeatPassword: String) {
        passwordRepeat = newRepeatPassword
        hasPasswordError = (newRepeatPassword != password)
    }

    fun changeUsername(newUsername: String) {
        username = newUsername
        hasUsernameError = validateInput.validateUsername(username)
    }

    fun changeLocation(newLocation: Location) {
        location = newLocation
        hasLocationError = location.locationName.isNotEmpty()
    }

}