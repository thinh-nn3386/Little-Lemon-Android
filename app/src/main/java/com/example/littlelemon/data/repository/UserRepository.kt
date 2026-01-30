package com.example.littlelemon.data.repository

import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.preferences.UserPreferences
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userPreferences: UserPreferences
) {
    fun isUserRegistered(): Boolean {
        return userPreferences.getUser().email != ""
    }

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

