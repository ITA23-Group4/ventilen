package com.example.ventilen_app.ui.screens.Chat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.ui.components.CustomMessageBox

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatLocalScreen(
    listOfLocationMessages: State<List<Message>>,
    isCurrentUserSender: (String) -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            reverseLayout = true
        ) {
            itemsIndexed(listOfLocationMessages.value) { index, message ->
                if (index == 0) {
                    Spacer(
                        modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                    )
                }
                CustomMessageBox(
                    message = message,
                    currentUserIsSender = isCurrentUserSender(message.senderUID)
                )
                if (index == listOfLocationMessages.value.lastIndex) {
                    Spacer(
                        modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                    )
                }
            }
        }

    }
}