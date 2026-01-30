package com.example.littlelemon.screens.profile

import androidx.lifecycle.ViewModel
import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(userRepository.getUser())
    val state: StateFlow<User> = _state

    fun clearUser() {
        userRepository.clearUser()
    }
}