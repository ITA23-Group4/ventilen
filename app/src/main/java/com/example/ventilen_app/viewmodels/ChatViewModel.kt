package com.example.ventilen_app.viewmodels

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
import com.example.ventilen_app.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.util.Date

class ChatViewModel : ViewModel() {
    val userRepository = UserRepository() // TODO: Should be private
    private val chatRepository = ChatRepository()

    val locationsWithLatestMessages: MutableList<Location> = mutableStateListOf()
    val localMessages: StateFlow<List<Message>>
        get() = chatRepository.messagesFlow

    var currentMessage: String by mutableStateOf("")
    var selectedLocation: Location by mutableStateOf(
        Location(
            locationName = "",
            latestMessage = "",
            abbreviation = "",
            locationID = ""
        )
    )

    init {
        viewModelScope.launch {
            userRepository.getUser()
            getLatestMessagesFromEachLocation()
        }
    }

    fun getLocalMessages(locationID: String) {
        viewModelScope.launch {
            chatRepository.observeMessagesByLocation(locationID)
        }
    }

    fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            val latestMessages: List<Location> = chatRepository.chatHubMessagesSnapshot()
            locationsWithLatestMessages.clear()
            locationsWithLatestMessages.addAll(latestMessages)
        }
    }

    fun sendMessage() {
        val messageToSend = Message(
            senderUID = userRepository.currentUser?.uid!!,
            message = currentMessage,
            timestamp = Date(),
            locationID = selectedLocation.locationID!!,
            username = userRepository.currentUser?.username!!
        )

        viewModelScope.launch{
            chatRepository.sendMessage(
                messageToSend
            )

            currentMessage = ""
        }
    }

    fun isCurrentUserSender(messageUserUID: String): Boolean {
        return userRepository.currentUser!!.uid!! == messageUserUID
    }

}
