package com.example.littlelemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.screens.OnboardingScreen
import com.example.littlelemon.ui.screens.HomeScreen
import com.example.littlelemon.ui.screens.ProfileScreen


@Composable
fun Navigation() {
    val startDestination = remember {
        if (false) HomeRoute else OnBoardingRoute
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination // App starts here
    ) {
        composable<OnBoardingRoute> {
            OnboardingScreen()
        }
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}