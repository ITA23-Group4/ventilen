package com.example.ventilen_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Message

@Composable
fun CustomMessageBox(
    message: Message,
    currentUserIsSender: Boolean,
) {
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomEnd = if ( message.senderUID == currentUserID) 0.dp else 16.dp,
                bottomStart = if ( message.senderUID == currentUserID) 16.dp else 0.dp
                )
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            // TODO: HOW TF DO WE ALIGN THIS SHIT!
            //.align(if (message.senderUID == currentUserID) Alignment.CenterEnd else Alignment.CenterStart)
    ) {
        Text(text = message.username, style = MaterialTheme.typography.headlineMedium)
        Text(text = message.message, style = MaterialTheme.typography.headlineMedium)
    }
}
