package com.example.littlelemon.screens.onboarding

import androidx.lifecycle.ViewModel
import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state

    fun onFirstNameChanged(value: String) {
        updateState(firstName = value)
    }

    fun onLastNameChanged(value: String) {
        updateState(lastName = value)
    }

    fun onEmailChanged(value: String) {
        updateState(email = value, isInvalidEmail = false)
    }

    fun onRegisterClicked(onSuccess: () -> Unit) {
        val current = _state.value

        if (!isValidEmail(current.email)) {
            _state.update {
                it.copy(isInvalidEmail = true)
            }
            return
        }

        userRepository.saveUser(
            User(
                firstName = current.firstName,
                lastName = current.lastName,
                email = current.email
            )
        )

        onSuccess()
    }

    private fun updateState(
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        isInvalidEmail: Boolean? = null,
    ) {
        _state.update {
            val newState = it.copy(
                firstName = firstName ?: it.firstName,
                lastName = lastName ?: it.lastName,
                email = email ?: it.email,
                isInvalidEmail  = isInvalidEmail ?: it.isInvalidEmail
            )

            newState.copy(
                isRegisterEnabled = newState.firstName.isNotBlank()
                        && newState.lastName.isNotBlank()
                        && newState.email.isNotBlank(),
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}