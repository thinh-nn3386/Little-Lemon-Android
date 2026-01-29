package com.example.littlelemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.models.UserViewModel
import com.example.littlelemon.ui.screens.OnboardingScreen
import com.example.littlelemon.ui.screens.HomeScreen
import com.example.littlelemon.ui.screens.ProfileScreen


@Composable
fun Navigation(viewModel: UserViewModel = viewModel()) {
    val user by viewModel.userState.collectAsState()

    val startDestination = remember(user) {
        if (user.email.isNotBlank()) HomeRoute else OnBoardingRoute
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination // App starts here
    ) {
        composable<OnBoardingRoute> {
            OnboardingScreen(
                navController
            )
        }
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen(
                navController
            )
        }
    }
}