package com.example.ventilen_app.ui.components.scaffolds
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
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

