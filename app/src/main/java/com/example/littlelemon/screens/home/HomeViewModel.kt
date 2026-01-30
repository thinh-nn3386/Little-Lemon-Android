package com.example.littlelemon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.model.MenuItem
import com.example.littlelemon.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        observeMenu()
        refreshIfNeeded()
    }

    private fun observeMenu() {
        viewModelScope.launch {
            menuRepository.observeMenu()
                .collect { items ->
                    applyFilter(items)
                }
        }
    }

    private fun refreshIfNeeded() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                menuRepository.refreshMenuIfNeeded()
            } catch (e: Exception) {
                _state.update {
                    it.copy(errorMessage = e.message)
                }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
    }

    private fun applyFilter(items: List<MenuItem>) {
        val query = _state.value.searchQuery

        val filtered = if (query.isBlank()) {
            items
        } else {
            items.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }

        _state.update {
            it.copy(menuItems = filtered)
        }
    }
}