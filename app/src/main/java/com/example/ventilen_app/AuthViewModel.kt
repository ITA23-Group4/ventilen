package com.example.ventilen_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    var username:String by mutableStateOf("")
    var email:String by mutableStateOf("")
    var location:String by mutableStateOf("")
    var password:String by mutableStateOf("")
}