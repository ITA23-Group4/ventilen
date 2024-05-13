package com.example.ventilen_app.generalViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.repositories.ChatRepository
import com.example.ventilen_app.data.models.Message
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepository()

    var currentMessage: String by mutableStateOf("")
    var selectedLocationChatID by mutableStateOf("")

    // TODO: ADD STATE :(
    var locationsWithLatestMessages: List<Location> = emptyList<Location>()
    val localMessages: StateFlow<List<Message>> get() = chatRepository.messagesFlow

    init {
        getLatestMessagesFromEachLocation()
    }

    fun getLocalMessages(locationID: String) {
        viewModelScope.launch {
            chatRepository.observeMessagesByLocation(locationID)
        }
    }
    fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            val latestMessages = chatRepository.chatHubMessagesSnapshot()
            locationsWithLatestMessages = latestMessages
        }
    }

    fun sendMessage(
        message: Message
    ) {
        viewModelScope.launch{
            chatRepository.sendMessage(
                message
            )
            currentMessage = ""
        }
    }

    fun isCurrentUserSender(currentUserUID: String, messageUserUID: String): Boolean {
        return currentUserUID == messageUserUID
    }

}
