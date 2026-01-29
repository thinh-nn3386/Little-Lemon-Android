package com.example.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.model.UserViewModel
import com.example.littlelemon.screens.onboarding.OnboardingScreen
import com.example.littlelemon.screens.home.HomeScreen
import com.example.littlelemon.screens.profile.ProfileScreen


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
            HomeScreen(
                navController
            )
        }
        composable<ProfileRoute> {
            ProfileScreen(
                navController
            )
        }
    }
}