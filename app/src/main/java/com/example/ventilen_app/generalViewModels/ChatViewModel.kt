package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.ChatRepository
import com.example.ventilen_app.data.models.LocationInfo
import com.example.ventilen_app.data.models.Message
import kotlinx.coroutines.launch

// TODO Parameter:
// - current route
// - primaryLocationID
class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    // LiveData list of messages for observing real-time changes
    val messages: LiveData<List<Message>> = if (true) {
            repository.observeMessages("123")
        } else {
            repository.observeMessages("123")
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
            Log.d("LLMLD", latestMessages.toString())
        }
    }



    fun sendMessage(senderUID: String, messageContent: String) {
        viewModelScope.launch{
            repository.sendMessage(senderUID, messageContent)
        }
    }



}
