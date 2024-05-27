package com.example.ventilen_app.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.repositories.EventRepository
import com.example.ventilen_app.data.repositories.LocationRepository
import com.example.ventilen_app.data.repositories.UserRepository
import com.example.ventilen_app.services.AccountService
import com.example.ventilen_app.utils.ValidateInput
import kotlinx.coroutines.launch

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
class AuthViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()
    private val locationRepository: LocationRepository = LocationRepository
    private val userRepository: UserRepository = UserRepository
    private val eventRepository: EventRepository = EventRepository
    private val validateInput: ValidateInput = ValidateInput()

    init {
        viewModelScope.launch {
            locationRepository.getLocations()
        }
    }

    var email: String by mutableStateOf("")
    var hasEmailError: Boolean by mutableStateOf(false)

    var password: String by mutableStateOf("")
    var passwordRepeat: String by mutableStateOf("")
    var hasPasswordError: Boolean by mutableStateOf(false)
    var hasPasswordRepeatedError: Boolean by mutableStateOf(false)

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

    var hasLoginError: Boolean by mutableStateOf(false)

    var showDialog: Boolean by mutableStateOf(false)

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
                userRepository.getUser()
                eventRepository.currentUserPrimaryLocationID = userRepository.currentUser!!.primaryLocationID
                eventRepository.getEvents()
                onLoginSuccess()
            } catch (error: Exception) {
                Log.e("LOG IN", "Failed to log in: $error")
                onLoginFailure()
            }

        }
    }

    fun credentialsFieldsNotEmptyAndValid(): Boolean {
        val hasErrors: Boolean = hasEmailError && hasPasswordError && hasPasswordRepeatedError
        val isNotEmpty: Boolean = email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty()
        return !hasErrors && isNotEmpty
    }

    fun usernameFieldNotEmptyAndValid(): Boolean {
        val hasErrors: Boolean = hasUsernameError
        val isNotEmpty: Boolean = username.isNotEmpty()
        return !hasErrors && isNotEmpty
    }

    fun getLocationName(): List<String> {
        return locationRepository.locations.map { it.locationName }
    }

    fun onLocationValueChanged(selectedLocationName: String) {
        val selectedLocation = locationRepository.mapLocationNameToLocation[selectedLocationName]!!
        // Check if selectedLocation is not null before using it
        changeLocation(selectedLocation)
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
        hasPasswordRepeatedError = (newRepeatPassword != password)
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