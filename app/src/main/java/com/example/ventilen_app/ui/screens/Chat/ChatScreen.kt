package com.example.ventilen_app.ui.screens.Chat

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.components.CustomColumn

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel,
    currentUserViewModel: CurrentUserViewModel
) {
    CustomColumn {

        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineLarge)
        // CustomChatCard


        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineLarge)
        // CustomChatCards in a LazyColumn

        LazyColumn {
            chatViewModel.messages.value?.let { messages ->
                items(messages.filter
                // Filter to only show messages from the current user's primary location
                { it.locationID == currentUserViewModel.currentUser?.primaryLocationID }
                ) { message ->
                    Text(
                        text = "${message.senderUID}: ${message.message}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Log.d("CHAT", "${message.senderUID}: ${message.message}")
                }
            }
        }
    }

}
