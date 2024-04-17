package com.example.ventilen_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    private val username:String by mutableStateOf("")
    private val email:String by mutableStateOf("")
    private val location:String by mutableStateOf("")
    private val password:String by mutableStateOf("")
}