package com.example.littlelemon.ui.screens

import android.util.Patterns
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
import androidx.compose.ui.tooling.preview.Preview
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
fun OnboardingScreen(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isInvalidEmail by remember { mutableStateOf(false) }


    fun onRegister() {
        if (isValidEmail(email).not()) {
            isInvalidEmail = true
            return
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            return
        }
        viewModel.saveUser(firstName, lastName, email)
        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

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
                enabled = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank(),
                label = "Register",
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                onClick = ::onRegister
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Green)
                    .padding(vertical = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Let's get to know you!",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Personal information",
                    modifier = Modifier.padding(vertical = 24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
                AppTextInput(
                    required = true,
                    label = "First name",
                    value = firstName,
                    onValueChange = { firstName = it },
                    modifier = Modifier
                        .focusRequester(focusRequester1),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            if (firstName.isNotBlank()) {
                                focusRequester2.requestFocus()
                            } else {
                                keyboardController?.hide()
                            }
                        }
                    ),
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )
                AppTextInput(
                    required = true,
                    label = "Last name",
                    value = lastName,
                    onValueChange = { lastName = it },
                    modifier = Modifier
                        .focusRequester(focusRequester2),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            if (lastName.isNotBlank()) {
                                focusRequester3.requestFocus()
                            } else {
                                keyboardController?.hide()
                            }
                        }
                    ),
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )
                AppTextInput(
                    required = true,
                    label = "Email",
                    value = email,
                    onValueChange = {
                        email = it
                        isInvalidEmail = false
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester3),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.None,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onRegister()
                        }
                    ),
                    containerModifier = Modifier.padding(vertical = 8.dp)
                )

                // ✅ Error text with Animation
                AnimatedVisibility(
                    visible = isInvalidEmail,
                    enter = expandVertically(animationSpec = tween(300)) + fadeIn(),
                    exit = shrinkVertically(animationSpec = tween(300)) + fadeOut()
                ) {
                    Text(
                        text = "This Email is invalid",
                        color = Color.Red,
                        fontFamily = Markazi,
                        style = MaterialTheme.typography.bodyMedium, // Suggested for error labels
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
