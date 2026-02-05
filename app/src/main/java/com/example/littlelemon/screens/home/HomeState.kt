package com.example.littlelemon.screens.home

import com.example.littlelemon.data.model.MenuItem


data class HomeState(
    val isLoading: Boolean = false,
    val menuItems: List<MenuItem> = emptyList(),
    val menuItemsCategory: List<String> = emptyList(),
    val selectedCategory: String = "",
    val searchQuery: String = "",
    val errorMessage: String? = null
)