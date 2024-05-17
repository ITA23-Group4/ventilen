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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.util.Date

class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepository()

    private var currentUserUsername: String by mutableStateOf("") // TODO: get username

    fun getCurrentUserUIDFromFirebase(): String {
        return FirebaseAuth.getInstance().currentUser?.uid!!
    }

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
            getLatestMessagesFromEachLocation()
        }
    }

    fun getLocalMessages(locationID: String) {
        viewModelScope.launch {
            chatRepository.observeMessagesByLocation(locationID)
        }
    }

    private fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            locationsWithLatestMessages.clear()
            locationsWithLatestMessages.addAll(chatRepository.chatHubMessagesSnapshot())
        }
    }

    fun sendMessage() {
        val messageToSend = Message(
            senderUID = getCurrentUserUIDFromFirebase(),
            message = currentMessage,
            timestamp = Date(),
            locationID = selectedLocation.locationID!!,
            username = getCurrentUserUIDFromFirebase() // TODO: This should be username and not UID
        )

        viewModelScope.launch{
            chatRepository.sendMessage(
                messageToSend
            )

            currentMessage = ""
        }
    }

    fun isCurrentUserSender(messageUserUID: String): Boolean {
        return getCurrentUserUIDFromFirebase() == messageUserUID
    }

}
