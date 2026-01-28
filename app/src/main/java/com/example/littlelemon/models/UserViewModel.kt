package com.example.littlelemon.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    val userState: StateFlow<User> = userPreferences.userFlow

    fun saveUser(firstName: String, lastName: String, email: String) {
        val user = User(firstName, lastName, email)
        userPreferences.saveUser(user)
    }

    fun clearUser() {
        userPreferences.clearUser()
    }
}