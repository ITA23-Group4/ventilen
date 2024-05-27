package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun UsernameScreen(
    textUsername: String,
    hasUsernameError: Boolean,
    onNavigateLocation: () -> Unit,
    usernameFieldsNotEmpty: () -> Boolean,
    onValueChange: (String) -> Unit,

    // Dialog
    showDialog: Boolean,
    onInformationClick: () -> Unit,
    dismissDialog: () -> Unit,
    ) {
    CustomColumn(modifier = Modifier
        .fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        if (showDialog){
            AlertDialog(
                onDismissRequest = { dismissDialog() },
                confirmButton = {
                    CustomFilledButton(
                        text = "OK",
                        onClick = dismissDialog
                    )
                },
                title = { Text(text = "Information") },
                text = {
                    Text(
                        text = "Et anonymt username sikre din identitet ikke kan gennemskues. Et eksempel på dette kunne være: AnonymGiraf42, HestePersonen68.\n\nUsername skal min. være 6 tegn og kan kun indholde tal og bogstaver"
                    ) }
            )
        }

        TopAuthPageDesign(
            topText = "Hvad skal vi kalde dig",
            bottomText = "Indtast dit anonyme username",
            hasInformationButton = true,
            onInformationClick = onInformationClick
        )

        CustomTextField(
            text = textUsername,
            label = "Username",
            onValueChange = {onValueChange(it)},
            hasError = hasUsernameError,
            errorMessage = "Username skal min. være 6 tegn og kan kun indholde tal og bogstaver"
        )
        CustomFilledButton(
            text = "Fortsæt",
            onClick = onNavigateLocation,
            isEnabled = usernameFieldsNotEmpty()
        )

    }

}