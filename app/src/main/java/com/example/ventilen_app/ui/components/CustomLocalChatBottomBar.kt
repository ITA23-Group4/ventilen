package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun CustomLocalChatBottomBar(
    currentMessage: String,
    onCurrentMessageChanged: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 20.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp,
                    start = 4.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CustomTextField(
                text = currentMessage,
                onValueChange = { onCurrentMessageChanged(it) },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                label = ""
            )
            IconButton(onClick = onSendMessage) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(top = 6.dp)
                )
            }
        }
    }
}