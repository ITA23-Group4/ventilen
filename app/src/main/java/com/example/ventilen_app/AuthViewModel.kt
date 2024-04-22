package com.example.ventilen_app

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.services.AccountService

class AuthViewModel: ViewModel() {
    private val accountService: AccountService = AccountService();
    val repository: Repository = Repository()

    var username:String by mutableStateOf("")
    var email:String by mutableStateOf("")
    var location:String by mutableStateOf("")
    var password:String by mutableStateOf("")

    fun registerNewUser(navigateOnSuccess: (String) -> Unit, navigateOnFail: () -> Unit) {
        accountService.authenticate(email, password, navigateOnSuccess, navigateOnFail)
        /*
        repository.db.collection("users")
            .document(it) // Make UID for AUTH and users document UID the same
            .set(newUser)
            .addOnSuccessListener {
                Log.d("CREATED", "CREATED NEW USER")
                navController.navigate("home")
                authViewModel.loginUser(
                    navigateOnSuccess = {
                        currentUserViewModel.getCurrentUser()
                        navController.navigate("home")
                    },
                    navigateOnFail = {
                        Log.d("FAILED", "FAILED TO CREATE NEW USER1")
                    }
                )
            }
            .addOnFailureListener {
                Log.d("FAILED", "FAILED TO CREATE NEW USER2")
            }

         */
    }

    fun loginUser(navigateOnSuccess: () -> Unit, navigateOnFail: () -> Unit) {
        accountService.login(email, password, navigateOnSuccess, navigateOnFail)
    }

}