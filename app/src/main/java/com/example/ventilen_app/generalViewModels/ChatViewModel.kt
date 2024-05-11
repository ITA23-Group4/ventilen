package com.example.ventilen_app.generalViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.repositories.ChatRepository
import com.example.ventilen_app.data.models.LocationInfo
import com.example.ventilen_app.data.models.Message
import kotlinx.coroutines.launch

// TODO Parameter:
// - current route
// - primaryLocationID
class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    var selectedLocationChatID by mutableStateOf("") // Might be a solution to select the right chat - idk if it's the best way

    // LiveData list of messages for observing real-time changes
    val messages: LiveData<List<Message>> = if (true) {
            repository.observeMessages()
        } else {
            repository.observeMessages()
        }

    /*

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

     */

    // A non-mutable list of messages
    // It is non-mutable because it is run every time the screen is re-rendered
    // Therefore it does not need to be mutable since it is re-initialized every time
    var latestMessagesFromEachLocation: List<LocationInfo> = emptyList<LocationInfo>()
    // The function below is called whenever we navigate to the ChatHubScreen
    fun getLatestMessagesFromEachLocation() {
        viewModelScope.launch {
            val latestMessages = repository.chatHubMessagesSnapshot()
            latestMessagesFromEachLocation = latestMessages
        }
    }

    // A mutable livedata list of local messages
    // Scope needed??
    var localMessages: LiveData<List<Message>> = MutableLiveData<List<Message>>()
    fun getLocalMessages(locationID: String) {
        viewModelScope.launch {
            val messages = repository.observeMessagesByLocation(locationID)
            localMessages = messages
        }
    }
    // Think below is correct but the observer needs to be terminated when exiting the screen!
    // localMessages: LiveData<List<Message>> = repository.observeMessagesByLocation(locationID)



    fun sendMessage(
        senderUID: String,
        messageContent: String,
        senderUsername: String,
        locationID: String
    ) {
        viewModelScope.launch{
            repository.sendMessage(
                senderUID,
                messageContent,
                senderUsername,
                locationID
            )
        }
    }




}
