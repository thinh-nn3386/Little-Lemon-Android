package com.example.littlelemon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.text.contains

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    // 1. The UI State (Search query, loading, etc.)
    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    // 1. Create a separate Flow for categories.
    // It only triggers when the database rawItems change.
    private val _categories = menuRepository.observeMenu()
        .map { items -> items.map { it.category }.distinct() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Combine the user inputs first
    val filterFlow = combine(_searchQuery, _selectedCategory) { query, cat -> query to cat }

    // 2. The Filtered State (Derived from Database + Search Query)
    val state: StateFlow<HomeState> = combine(
        menuRepository.observeMenu(), // The source from Room
        _categories,
        filterFlow,
        _isLoading,
        _errorMessage
    ) { rawItems, categories, filters, loading, error ->
        val (query, category) = filters
        val filteredItemsByCategory = if (category.isBlank()) {
            rawItems
        } else {
            rawItems.filter { it.category == category }
        }

        val filteredItems = if (query.isBlank()) {
            filteredItemsByCategory
        } else {
            filteredItemsByCategory.filter { it.title.contains(query, ignoreCase = true) }
        }

        HomeState(
            menuItems = filteredItems,
            menuItemsCategory = categories,
            selectedCategory = category,
            searchQuery = query,
            isLoading = loading,
            errorMessage = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState(isLoading = true)
    )

    init {
        refreshMenu()
    }

    fun onSelectItemCategory(category: String) {
        // Toggle category: if same is clicked, clear filter
        _selectedCategory.value = if (_selectedCategory.value == category) "" else category
    }


    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun refreshMenu() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                menuRepository.refreshMenuIfNeeded()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}