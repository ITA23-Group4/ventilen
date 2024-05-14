package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.PassordValidationState
import com.example.ventilen_app.data.models.ValidatePassword
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class CredentialsScreenVievModel(
    private val validatePassword: ValidatePassword = ValidatePassword()
): ViewModel() {

    var password by mutableStateOf("")
        private set



}