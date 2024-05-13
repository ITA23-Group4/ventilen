package com.example.ventilen_app.data.models

data class PassordValidationState(
    val hasMinimun: Boolean = false,
    val hasCapitalizedLetter: Boolean = false,
    val hasNumber: Boolean = false,
    val successful: Boolean = false
)
