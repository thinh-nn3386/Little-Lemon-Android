package com.example.littlelemon.navigation

import androidx.lifecycle.ViewModel
import com.example.littlelemon.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val startDestination = if (userRepository.isUserRegistered()) {
            HomeRoute
        } else {
            OnBoardingRoute
        }
}