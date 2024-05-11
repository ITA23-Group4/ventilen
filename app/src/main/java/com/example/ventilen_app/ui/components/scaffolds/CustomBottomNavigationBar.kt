package com.example.ventilen_app.ui.components.scaffolds

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function to create a custom bottom navigation bar.
 * NOTE: NavigationBarItem's has a lot of redundant code, but it is not possible
 * to create a custom NavigationBarItem as is now.
 *
 * @param currentRoute The currently selected route.
 * @param onNavigateHome Callback function for navigating to the home route.
 * @param onNavigateChat Callback function for navigating to the chat route.
 * @param onNavigateEvent Callback function for navigating to the event route.
 *
 * @author Marcus
 */
@Composable
fun CustomBottomNavigationBar(
    currentRoute: String,
    onNavigateHome: () -> Unit = {},
    onNavigateChat: () -> Unit = {},
    onNavigateEvent: () -> Unit = {}
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = onNavigateHome,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledIconColor = MaterialTheme.colorScheme.surface,
                disabledTextColor = MaterialTheme.colorScheme.surface
            ),
            label = { Text(text = "Home", style = MaterialTheme.typography.headlineMedium) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == "chat",
            onClick = onNavigateChat,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledIconColor = MaterialTheme.colorScheme.surface,
                disabledTextColor = MaterialTheme.colorScheme.surface
            ),
            label = { Text(text = "Chat", style = MaterialTheme.typography.headlineMedium) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Chat",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == "event",
            onClick = onNavigateEvent,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledIconColor = MaterialTheme.colorScheme.surface,
                disabledTextColor = MaterialTheme.colorScheme.surface
            ),
            label = { Text(text = "Events", style = MaterialTheme.typography.headlineMedium) },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Event",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
    }
}