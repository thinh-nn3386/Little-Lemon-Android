package com.example.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.screens.onboarding.OnboardingScreen
import com.example.littlelemon.screens.home.HomeScreen
import com.example.littlelemon.screens.profile.ProfileScreen


@Composable
fun Navigation(viewModel: AppViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = viewModel.startDestination // App starts here
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