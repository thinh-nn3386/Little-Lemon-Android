package com.example.littlelemon.screens.onboarding

class OnboardingViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun register(user: User) {
        userRepository.saveUser(user)
    }
}