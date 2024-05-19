package com.example.ventilen_app.ui.screens.credentials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.theme.VentilenAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// This is a single test for the entire screen
// The test verifies that the text fields for email, password, and repeat password are displayed
// The test also verifies that the text fields contain the user input
// The test could be split into multiple tests for each text field for more granularity (details)
@RunWith(AndroidJUnit4::class)
class CredentialsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCredentialsScreenTextFields() {
        // Set up initial state for the CredentialsScreen
        var emailText by mutableStateOf("")
        var passwordText by mutableStateOf("")
        var repeatPasswordText by mutableStateOf("")
        var hasEmailError by mutableStateOf(false)
        var hasPasswordError by mutableStateOf(false)

        // Set the content of the Compose test
        composeTestRule.setContent {
            VentilenAppTheme {
                CredentialsScreen(
                    onNavigateUsername = {},
                    textEmail = emailText,
                    hasEmailError = hasEmailError,
                    password = passwordText,
                    repeatPassword = repeatPasswordText,
                    hasPasswordError = hasPasswordError,
                    onValueChangeEmail = { emailText = it },
                    onValueChangePassword = { passwordText = it },
                    onValueChangePasswordRepeat = { repeatPasswordText = it }
                )
            }
        }

        // Verify that the email text field is displayed
        composeTestRule.onNodeWithText("Email").assertExists("Email text field should be displayed")

        // Verify that the password text field is displayed
        composeTestRule.onNodeWithText("Password").assertExists("Password text field should be displayed")

        // Verify that the repeat password text field is displayed
        composeTestRule.onNodeWithText("Gentag Password").assertExists("Repeat password text field should be displayed")

        // Simulate user input for email
        emailText = "test@example.com"

        // Simulate user input for password
        passwordText = "password123"

        // Simulate user input for repeat password
        repeatPasswordText = "password123"

        // Perform assertions based on the updated state
        composeTestRule
            .onNodeWithText(text = emailText)
            .assertExists("Email text field should contain the user input")
        composeTestRule
            .onNodeWithText(text = passwordText)
            .assertExists("Password text field should contain the user input")
        composeTestRule
            .onNodeWithText(text = repeatPasswordText)
            .assertExists("Repeat password text field should contain the user input")
    }
}
