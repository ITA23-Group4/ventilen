package com.example.ventilen_app.ui.screens.Chat

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomChatCard
import com.example.ventilen_app.ui.components.CustomColumn

@Composable
fun ChatHubScreen(
    chatViewModel: ChatViewModel,
    currentUserViewModel: CurrentUserViewModel
) {
    CustomColumn {
        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineLarge)
        CustomChatCard()

        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineLarge)
        // CustomChatCards in a LazyColumn

        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            chatViewModel.messages.value?.let { messages ->
                items(messages.filter
                // Filter to only show messages from the current user's primary location
                { it.locationID == currentUserViewModel.currentUser?.primaryLocationID }
                ) { message ->
                    CustomChatCard()
                    // Text(
                    //    text = "${message.senderUID}: ${message.message}",
                    //    style = MaterialTheme.typography.headlineMedium
                    //)
                    Log.d("CHAT", "${message.senderUID}: ${message.message}")
                }
            }
        }
    }

}
