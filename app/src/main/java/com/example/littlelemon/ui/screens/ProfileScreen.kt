package com.example.littlelemon.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.models.UserViewModel
import com.example.littlelemon.ui.components.AppButton
import com.example.littlelemon.ui.components.AppTextInput
import com.example.littlelemon.ui.navigation.HomeRoute
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Markazi

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.userState.collectAsState()

    fun onLogOut() {
        viewModel.clearUser()

        navController.navigate(HomeRoute) {
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
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),
                    contentScale = ContentScale.Fit
                )
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