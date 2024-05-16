package com.example.ventilen_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Message

@Composable
fun CustomMessageBox(
    message: Message,
    currentUserIsSender: Boolean,
) {
    // TODO: Make function in ChatViewModel --> should not have functionality inside Composable
    val background = if (currentUserIsSender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val horizontalArrangement = if (currentUserIsSender) Arrangement.End else Arrangement.Start
    val bottomEnd = if (currentUserIsSender) 0.dp else 16.dp
    val bottomStart = if (currentUserIsSender) 16.dp else 0.dp
    val textStyleHeadline = if (currentUserIsSender) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.headlineMedium
    val textStyleBody = if (currentUserIsSender) MaterialTheme.typography.bodySmall else MaterialTheme.typography.headlineMedium

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomEnd = bottomEnd,
                        bottomStart = bottomStart
                    )
                )
                .background(background)
                .padding(12.dp)
        ) {
            Text(text = message.username, style = textStyleHeadline)
            Text(text = message.message, style = textStyleBody)
        }
    }
}
