package com.example.ventilen_app.generalViewModels

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class AdminViewModel : UserViewModel() {
    override val isAdmin: Boolean = true

    fun logout() {
        Log.d("LOGGED OUT", "logout: logging out")
        FirebaseAuth.getInstance().signOut()
    }

}