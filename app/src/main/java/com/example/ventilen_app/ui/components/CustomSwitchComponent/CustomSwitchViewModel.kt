package com.example.ventilen_app.ui.components.CustomSwitchComponent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CustomSwitchViewModel: ViewModel() {
    var isChecked:Boolean by mutableStateOf(false)

    fun toggle() {
        isChecked = !isChecked
    }
}
