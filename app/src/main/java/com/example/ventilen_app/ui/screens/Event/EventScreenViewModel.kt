package com.example.ventilen_app.ui.screens.Event

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.Event
import kotlinx.coroutines.launch

class EventScreenViewModel: ViewModel() {
    private val repository: Repository = Repository()
    var events: MutableList<Event> by mutableStateOf(mutableStateListOf())
    var currentEventAttendeesCount: Int by mutableIntStateOf(0)
    init {
        getEvents()
    }

    fun getEvents(){
        viewModelScope.launch {
            try {
                events = repository.getEvents()
                Log.d("get events", events.toString())
            } catch (error: Exception) {
                Log.d("ERROR",error.toString())
            }
        }
    }
    fun getEventByID(eventID: String){
        viewModelScope.launch {
            try {
                val currentEvent = repository.getEvent(eventID = eventID)
                currentEventAttendeesCount = currentEvent.attendeesByUID.size
                Log.d("get events", "Event retrieved")
            } catch (error: Exception) {
                Log.d("ERROR",error.toString())
            }
        }
    }



    fun addUserToEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            repository.addUserToEvent(currentUserUID, eventID)
        }
    }

    fun removeUserFromEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            repository.removeUserFromEvent(currentUserUID, eventID)
        }
    }


}