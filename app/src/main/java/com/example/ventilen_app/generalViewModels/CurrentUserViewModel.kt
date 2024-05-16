package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.User
import com.example.ventilen_app.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.lang.Exception

open class CurrentUserViewModel : ViewModel() {
    val userRepository: UserRepository = UserRepository()
    var currentUser: User? by mutableStateOf(User("username"))
    open val isAdmin: Boolean = false

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                val currentUserUID: String = FirebaseAuth.getInstance().currentUser!!.uid
                currentUser = userRepository.getUser(currentUserUID)
                Log.d("CurrentUser:", "Username = ${currentUser?.username.toString()} primaryLocation = ${currentUser?.primaryLocationID.toString()} UID = ${currentUser?.uid.toString()}")
            } catch (error: Exception) {
                Log.d("ERROR", error.toString());
            }
        }
    }

    fun getUID(): String {
        return currentUser!!.uid!!
    }

}