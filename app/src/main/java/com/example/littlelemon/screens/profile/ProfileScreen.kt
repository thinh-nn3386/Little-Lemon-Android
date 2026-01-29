package com.example.littlelemon.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.data.model.UserViewModel
import com.example.littlelemon.ui.components.AppButton
import com.example.littlelemon.ui.components.AppTextInput
import com.example.littlelemon.navigation.OnBoardingRoute

@Composable
fun ProfileScreen(
    navController: NavController? = null,
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.userState.collectAsState()

    fun onLogOut() {
        viewModel.clearUser()

        navController?.navigate(OnBoardingRoute) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), // Optional padding for visual breathing room
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(200.dp)
                        .height(56.dp),
                    contentScale = ContentScale.Fit
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp),
                    onClick = {
                        navController?.popBackStack()
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        bottomBar = {
            AppButton(
                label = "Log out",
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                onClick = ::onLogOut
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Personal information",
                    modifier = Modifier.padding(bottom = 24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
                AppTextInput(
                    enabled = false,
                    label = "First name",
                    value = user.firstName,
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )
                AppTextInput(
                    enabled = false,
                    label = "First name",
                    value = user.lastName,
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )
                AppTextInput(
                    enabled = false,
                    label = "First name",
                    value = user.email,
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen()
}