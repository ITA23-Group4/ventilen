package com.example.ventilen_app.ui.components.CustomEventCardComponent

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CustomEventCardViewModel(
    eventAttendeesCount: Int = 0,

): ViewModel() {
    var attendeesCount: Int by mutableIntStateOf(eventAttendeesCount)

    fun addToEvent(
        onAddToEvent: suspend () -> Unit,
        onUpdateEvent: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                onAddToEvent()
                onUpdateEvent()
                Log.d("","")
            }
            catch (error: Exception) {
                Log.d("","")
            }
        }
    }

    fun removeFromEvent(
        onRemoveFromEvent: suspend () -> Unit,
        onUpdateEvent: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                onRemoveFromEvent()
                onUpdateEvent()
                Log.d("","")
            }
            catch (error: Exception) {
                Log.d("","")
            }
        }
    }

}