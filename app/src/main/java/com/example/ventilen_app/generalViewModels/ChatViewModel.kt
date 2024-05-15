package com.example.ventilen_app.generalViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.repositories.ChatRepository
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.util.Date

// TODO Parameter:
// - current route
// - primaryLocationID
class ChatViewModel : ViewModel() {
    private val userRepository = UserRepository(viewModelScope)
    private val chatRepository = ChatRepository()

    var currentMessage: String by mutableStateOf("")
    var selectedLocationChatID by mutableStateOf("") // Might be a solution to select the right chat - idk if it's the best way

    // LiveData list of messages for observing real-time changes
    //val messages: LiveData<List<Message>> = chatRepository.observeMessages()
    // TODO: ADD STATE :(
    var locationsWithLatestMessages: List<Location> = emptyList<Location>()

    val messages: MutableList<Message> = mutableStateListOf() // MutableState to hold the list of messages

    init {
        getLatestMessagesFromEachLocation()
        observeMessages()
    }

    private fun observeMessages() {
        chatRepository.observeMessages { messages: List<Message> ->
            this.messages.clear()
            this.messages.addAll(messages)
        }
    }

    // A non-mutable list of messages
    // It is non-mutable because it is run every time the screen is re-rendered
    // Therefore it does not need to be mutable since it is re-initialized every time
    // The function below is called whenever we navigate to the ChatHubScreen
    fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            val latestMessages = chatRepository.chatHubMessagesSnapshot()
            locationsWithLatestMessages = latestMessages
        }
    }

    // A mutable livedata list of local messages
    // Scope needed??
    var localMessages: LiveData<List<Message>> = MutableLiveData<List<Message>>()
    fun getLocalMessages(locationID: String) {
        viewModelScope.launch {
            val messages = chatRepository.observeMessagesByLocation(locationID)
            localMessages = messages
        }
    }

    fun sendMessage() {
        val messageToSend = Message(
            senderUID = userRepository.currentUser?.uid!!,
            message = currentMessage,
            timestamp = Date(),
            locationID = selectedLocationChatID,
            username = userRepository.currentUser?.username!!
        )

        viewModelScope.launch{
            chatRepository.sendMessage(
                messageToSend
            )

            currentMessage = ""
        }
    }

}
