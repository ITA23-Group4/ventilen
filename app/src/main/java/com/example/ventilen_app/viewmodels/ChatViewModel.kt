package com.example.ventilen_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.data.repositories.ChatRepository
import com.example.ventilen_app.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepository
    private val userRepository = UserRepository

    fun getCurrentUserUIDFromFirebase(): String {
        return FirebaseAuth.getInstance().currentUser?.uid!!
    }

    var locationsWithLatestMessages: MutableList<Location> = mutableStateListOf()

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

    fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            locationsWithLatestMessages = chatRepository.chatHubMessagesSnapshot().toMutableList()
        }
    }

    fun sendMessage() {
        val messageToSend = Message(
            senderUID = getCurrentUserUIDFromFirebase(),
            message = currentMessage,
            timestamp = Date(),
            locationID = selectedLocation.locationID!!,
            username = userRepository.currentUser!!.username
        )

        viewModelScope.launch{
            chatRepository.sendMessage(
                messageToSend
            )

            currentMessage = ""
        }
    }

    fun getCurrentUserPrimaryLocation(): Location {
        val currentUserPrimaryLocationID: String = userRepository.currentUser!!.primaryLocationID
        return locationsWithLatestMessages.find { it.locationID == currentUserPrimaryLocationID}!!
    }

    fun getLocationsExcludingPrimaryLocation(): List<Location> {
        val currentUserPrimaryLocationID: String = userRepository.currentUser!!.primaryLocationID
        return locationsWithLatestMessages.filter { it.locationID != currentUserPrimaryLocationID }
    }

    fun isCurrentUserSender(messageUserUID: String): Boolean {
        return getCurrentUserUIDFromFirebase() == messageUserUID
    }

}
