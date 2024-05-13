package com.example.ventilen_app.ui.screens.Chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomMessageBox
import com.example.ventilen_app.ui.components.CustomTextField
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatLocalScreen(
    listOfLocationMessages: State<List<Message>>,
    onSendMessage: () -> Unit, // To use when sending message
    currentMessage: String,
    onCurrentMessageChange: (String) -> Unit,
    isCurrentUserSender: (String) -> Boolean
) {
    // Scaffold added to have textfield at the bottom of the screen that then expands a keyboard when clicked
    Scaffold(
        content = {
            Column() {
                LazyColumn(
                    modifier = Modifier.height(600.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    reverseLayout = true
                ) {
                    items(listOfLocationMessages.value) { message ->
                        CustomMessageBox(
                            message = message,
                            currentUserIsSender = isCurrentUserSender(message.senderUID)
                        )
                        Log.d("CHAT", "${message.senderUID}: ${message.message}. LocationID: ${message.locationID}. Timestamp: ${message.timestamp}")
                    }
                }
                CustomFilledButton(text = "Send", onClick = onSendMessage )
                CustomTextField(
                    text = currentMessage,
                    label = "Message",
                    onValueChange = { onCurrentMessageChange(it) }
                )
            }
        }
    )


}