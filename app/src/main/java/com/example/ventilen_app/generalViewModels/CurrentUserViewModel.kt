package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.lang.Exception

class CurrentUserViewModel : ViewModel() {
    val repository: Repository = Repository()
    var currentUser: User? by mutableStateOf(User("username"))
    var selectedLocationChatID by mutableStateOf("") // Might be a solution to select the right chat - idk if it's the best way

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                val currentUserUID: String = FirebaseAuth.getInstance().currentUser!!.uid
                currentUser = repository.getUser(currentUserUID)
                Log.d("CurrentUser:", "Username = ${currentUser?.username.toString()} primaryLocation = ${currentUser?.primaryLocationID.toString()} UID = ${currentUser?.uid.toString()}")
            } catch (error: Exception) {
                Log.d("ERROR", error.toString());
            }
        }
    }

    // TODO: Remove
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

}