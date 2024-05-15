package com.example.ventilen_app.data.models

class ValidatePassword {

    fun execute(password: String): PasswordValidationState {
        val validateCapitalizedLetter = validateCapitalizedLetter(password)
        val validateNumber = validateNumber(password)
        val validateMinimun = validateMinimum(password)

        val hasError = listOf(
            validateMinimun,
            validateNumber,
            validateCapitalizedLetter
        ).all {it}

        return PasswordValidationState(
            hasMinimun = validateMinimun,
            hasCapitalizedLetter = validateCapitalizedLetter,
            hasNumber = validateNumber,
            successful = hasError
        )
    }

    private fun validateCapitalizedLetter(password: String): Boolean =
        password.contains(Regex(".*[A-Z].*"))

    private fun validateNumber(password: String): Boolean =
        password.contains(Regex(".*\\d.*"))

    private fun validateMinimum(password: String): Boolean =
        password.contains(Regex(".{6,}"))
}