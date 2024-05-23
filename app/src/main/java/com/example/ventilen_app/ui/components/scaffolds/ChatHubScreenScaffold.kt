package com.example.ventilen_app.ui.components.scaffolds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * ChatHubScreenScaffold is a scaffold component tailored for the chat hub screen. It provides
 * a customizable top app bar and a bottom navigation bar for navigation between different screens.
 *
 * @param currentRoute The current route used to highlight the selected item in the bottom navigation bar.
 * @param onNavigateEvent Callback function invoked when the event navigation item is clicked.
 * @param onNavigateHome Callback function invoked when the home navigation item is clicked.
 * @param content The main content to be displayed within the scaffold.
 * @author Christian, Nikolaj, Marcus
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatHubScreenScaffold(
    currentRoute: String,
    onNavigateEvent: () -> Unit,
    onNavigateHome: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                ,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                title = { Text("Chat", style = MaterialTheme.typography.headlineLarge) },
            )
        },
        bottomBar = {
            CustomBottomNavigationBar(
                currentRoute = currentRoute,
                onNavigateHome = onNavigateHome,
                onNavigateEvent = onNavigateEvent,
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
