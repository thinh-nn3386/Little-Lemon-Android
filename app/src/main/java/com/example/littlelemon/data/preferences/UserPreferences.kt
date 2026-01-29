package com.example.littlelemon.data.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.littlelemon.data.model.User

/**
 * Wrap SharedPreferences
 * Convert it into StateFlow
 * Single source of truth
 */
class UserPreferences(context: Context) {
    private val prefs =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun getUser(): User {
        return User(
            firstName = prefs.getString("first_name", "") ?: "",
            lastName = prefs.getString("last_name", "") ?: "",
            email = prefs.getString("email", "") ?: ""
        )
    }

    fun saveUser(user: User) {
        prefs.edit {
            putString("first_name", user.firstName)
            putString("last_name", user.lastName)
            putString("email", user.email)
        }
    }

    fun clearUser() {
        prefs.edit { clear() }
    }
}