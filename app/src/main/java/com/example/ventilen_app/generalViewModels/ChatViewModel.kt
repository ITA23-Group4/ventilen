package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.ChatRepository
import com.example.ventilen_app.data.models.Message
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    // LiveData list of messages for observing real-time changes
    private val messages: LiveData<List<Message>> = repository.observeMessages()

    // Mutable state list of messages for handling LazyColumn
    val mutableMessages: MutableList<Message> = mutableStateListOf()

    init {
        // Initialize the mutable list with the current messages
        viewModelScope.launch {
            mutableMessages.addAll(messages.value ?: emptyList())
        }

        // Observe changes in the LiveData list and update the mutable list accordingly
        messages.observeForever { messageList ->
            mutableMessages.clear()
            mutableMessages.addAll(messageList)
        }
    }

    fun sendMessage(senderUID: String, messageContent: String) {
        viewModelScope.launch{
            repository.sendMessage(senderUID, messageContent)
        }
    }
}
