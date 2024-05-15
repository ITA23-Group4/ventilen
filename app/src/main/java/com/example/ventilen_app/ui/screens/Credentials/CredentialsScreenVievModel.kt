package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ventilen_app.utils.ValidateInput

class CredentialsScreenVievModel(
    private val validateInput: ValidateInput = ValidateInput()
): ViewModel() {

    var password by mutableStateOf("")
        private set



}