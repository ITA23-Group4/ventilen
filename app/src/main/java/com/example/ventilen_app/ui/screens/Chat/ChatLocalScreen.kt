package com.example.ventilen_app.ui.screens.Chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomChatCard
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomMessageBox
import com.example.ventilen_app.ui.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatLocalScreen(
    listOfLocationMessages: LiveData<List<Message>>,
    onSendMessage: () -> Unit // To use when sending message
) {
    // Scaffold added to have textfield at the bottom of the screen that then expands a keyboard when clicked
    Scaffold(
        content = {
            Column(

            ) {
                Text(text = "Messages in this location")

                Spacer(modifier = Modifier.weight(1f))

                CustomTextField(text = "", label = "Message") {

                }
            }
        }
    )


}