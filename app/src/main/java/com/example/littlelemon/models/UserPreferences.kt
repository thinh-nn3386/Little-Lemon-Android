package com.example.littlelemon.models

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.core.content.edit
import kotlinx.coroutines.flow.asStateFlow


/**
 * Wrap SharedPreferences
 * Convert it into StateFlow
 * Single source of truth
 */
class UserPreferences(context: Context) {
    private val prefs =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _userFlow = MutableStateFlow(loadUser())
    val userFlow: StateFlow<User> = _userFlow.asStateFlow()

    private fun loadUser(): User {
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
        _userFlow.value = user // 🔥 notify observers
    }

    fun clearUser() {
        prefs.edit { clear() }
        _userFlow.value = User()
    }
}
