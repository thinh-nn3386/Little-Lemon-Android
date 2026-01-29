package com.example.littlelemon.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.littlelemon.data.preferences.UserPreferences
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