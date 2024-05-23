package com.example.ventilen_app.ui.components.scaffolds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomBackButton
import com.example.ventilen_app.ui.components.CustomLocalChatBottomBar

/**
 * A scaffold for the local chat screen, providing a top app bar, a bottom chat bar, and a content area.
 *
 * @param locationName The name of the location to be displayed in the top app bar. Defaults to "Chat".
 * @param showBackButton Whether to show a back button in the top app bar. Defaults to true.
 * @param onNavigateBack The action to perform when the back button is clicked. Defaults to an empty function.
 * @param currentMessage The current message typed in the bottom chat bar.
 * @param onCurrentMessageChange The action to perform when the current message changes.
 * @param onSendMessage The action to perform when the send button is clicked in the bottom chat bar.
 * @param content The main content to be displayed within the scaffold.
 *
 * @author Christian, Nikolaj, Marcus
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalChatScaffold(
    locationName: String = "Chat",
    showBackButton: Boolean = true,
    onNavigateBack: () -> Unit = {},
    // For Bottom bar
    currentMessage: String,
    onCurrentMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                ,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                navigationIcon = {
                    if (showBackButton) {
                        CustomBackButton(onNavigateBack = onNavigateBack)
                    }
                },
                title = { Text(text = locationName, style = MaterialTheme.typography.headlineLarge) }
            )
        },
        bottomBar = {
            CustomLocalChatBottomBar(
                currentMessage = currentMessage,
                onCurrentMessageChanged = { onCurrentMessageChange(it) },
                onSendMessage = onSendMessage
            )
        },
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                content()
            }
        }
    )
}
