package com.example.ventilen_app.ui.components.CustomDropDown

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
class CustomDropDownMenuViewModel: ViewModel() {
    var isExpanded:Boolean by mutableStateOf(false)
}

