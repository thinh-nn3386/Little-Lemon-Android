package com.example.littlelemon.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    val userState: StateFlow<User> = userPreferences.userFlow

    fun saveUser(firstName: String, lastName: String, email: String) {
        val current = userState.value
        userPreferences.saveUser(current.copy(
            firstName = firstName,
            lastName = lastName,
            email = email
        ))
    }


    fun clearUser(value: String) {
        userPreferences.clearUser()
    }
}
