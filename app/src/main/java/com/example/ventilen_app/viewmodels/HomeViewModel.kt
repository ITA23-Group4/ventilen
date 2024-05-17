package com.example.ventilen_app.viewmodels

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ventilen_app.data.repositories.ChatRepository
import com.example.ventilen_app.data.repositories.LocationRepository
import com.example.ventilen_app.data.repositories.UserRepository
import java.util.Calendar
import java.util.Date

class HomeViewModel : ViewModel() {
    val userRepository: UserRepository = UserRepository
    val locationRepository: LocationRepository = LocationRepository
    val chatRepository: ChatRepository = ChatRepository
}