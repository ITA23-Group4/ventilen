package com.example.ventilen_app.ui.screens.Chat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.ui.components.CustomMessageBox

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatLocalScreen(
    listOfLocationMessages: State<List<Message>>,
    isCurrentUserSender: (String) -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 8.dp, 0.dp, 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            reverseLayout = true
        ) {
            items(listOfLocationMessages.value) { message ->
                CustomMessageBox(
                    message = message,
                    currentUserIsSender = isCurrentUserSender(message.senderUID)
                )
            }
        }
    }


    // CustomFilledButton(text = "Send", onClick = onSendMessage)
    /*CustomTextField(
        text = currentMessage,
        label = "Message",
        onValueChange = { onCurrentMessageChange(it) }
    )*/

}