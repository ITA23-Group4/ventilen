package com.example.ventilen_app.ui.components.scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavController
import com.example.ventilen_app.ui.components.CustomBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScaffold(
    showBackButton: Boolean = true,
    onNavigateBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(),
                navigationIcon = {
                    if (showBackButton) {
                        CustomBackButton(onNavigateBack = onNavigateBack)
                    }
                },
                title = { /* empty */ }
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

