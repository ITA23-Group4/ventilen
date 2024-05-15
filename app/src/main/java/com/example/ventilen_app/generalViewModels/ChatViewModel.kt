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
import com.example.ventilen_app.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.util.Date

class ChatViewModel : ViewModel() {
    private val userRepository = UserRepository(viewModelScope)
    private val chatRepository = ChatRepository()

    var currentMessage: String by mutableStateOf("")
    var selectedLocation: Location by mutableStateOf(
        Location(
            locationName = "",
            latestMessage = "",
            abbreviation = "",
            locationID = ""
        )
    )

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
