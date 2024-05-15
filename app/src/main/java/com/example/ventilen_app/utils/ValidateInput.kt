package com.example.ventilen_app.utils

import android.util.Patterns

class ValidateInput {

    fun validateEmail(email: String): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Validates a password based on the following criteria:
     * 1. Contains at least one capitalized letter.
     * 2. Contains at least one digit.
     * 3. Has a minimum length of 6 characters.
     *
     * @param password The password string to be validated.
     * @return `true` if the password meets all criteria, `false` otherwise.
     */
    fun validatePassword(password: String): Boolean {
        return listOf(
            validateCapitalizedLetter(password),
            validateNumber(password),
            validateMinimum(password)
        ).all { it }
    }

    // TODO: What more? Check for explicit words, etc. (what about underscore ?)
    fun validateUsername(username: String): Boolean {
        val usernamePattern = Regex("^[a-zA-Z0-9]{6,}$") // only letters and numbers
        return usernamePattern.matches(username)
    }

    private fun validateCapitalizedLetter(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    private fun validateNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    private fun validateMinimum(password: String): Boolean {
        return password.length >= 6
    }

}