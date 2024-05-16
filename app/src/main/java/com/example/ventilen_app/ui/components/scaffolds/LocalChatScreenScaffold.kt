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
import com.example.ventilen_app.ui.components.CustomBackButton
import com.example.ventilen_app.ui.components.CustomLocalChatBottomBar

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