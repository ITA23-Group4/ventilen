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

/**
 * AuthScaffold is a scaffold component tailored for authentication screens. It provides
 * a customizable top app bar with optional back navigation functionality for the welcome screen.
 *
 * @param showBackButton Determines whether to show the back button in the top app bar. Defaults to true.
 * @param onNavigateBack Callback function invoked when the back button is clicked.
 * @param content The content to be displayed within the scaffold.
 * @author Marcus, Christian, Nikolaj
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScaffold(
    showBackButton: Boolean = true,
    onNavigateBack: () -> Unit = {},
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
                title = { Text("Opret event", style = MaterialTheme.typography.headlineLarge) },
                navigationIcon = {
                    if (showBackButton) {
                        CustomBackButton(onNavigateBack = onNavigateBack)
                    }
                },
            )
        },
        content = { paddingValues: PaddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)) {
                content()
            }
        }
    )
}

