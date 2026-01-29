package com.example.littlelemon.data.repository

import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.preferences.UserPreferences

class UserRepository(
    private val userPreferences: UserPreferences
) {
    fun saveUser(user: User) {
        userPreferences.saveUser(user)
    }

    fun getUser(): User {
        return userPreferences.getUser()
    }

    fun clearUser() {
        userPreferences.clearUser()
    }
}

