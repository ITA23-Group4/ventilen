package com.example.ventilen_app.ui.components.scaffolds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * A scaffold for the home screen, providing a top app bar, a bottom navigation bar, and a content area.
 *
 * @param currentRoute The current route used to highlight the selected item in the bottom navigation bar.
 * @param onNavigateEvent The action to perform when the event navigation item is clicked.
 * @param onNavigateChat The action to perform when the chat navigation item is clicked.
 * @param content The main content to be displayed within the scaffold.
 *
 * @Author [Your Name]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(
    currentRoute: String,
    isAdmin: Boolean,
    onNavigateEvent: () -> Unit,
    onNavigateChat: () -> Unit,
    onCreateNews: () -> Unit,
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
                title = { Text("Home", style = MaterialTheme.typography.headlineLarge) },
                actions = {
                    if (isAdmin) {
                        IconButton(onClick = onCreateNews) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add news",
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            CustomBottomNavigationBar(
                currentRoute = currentRoute,
                onNavigateEvent = onNavigateEvent,
                onNavigateChat = onNavigateChat,
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
