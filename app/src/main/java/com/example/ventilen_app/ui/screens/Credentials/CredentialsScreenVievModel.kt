package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ventilen_app.data.models.ValidatePassword

class CredentialsScreenVievModel(
    private val validatePassword: ValidatePassword = ValidatePassword()
): ViewModel() {

    var password by mutableStateOf("")
        private set



}