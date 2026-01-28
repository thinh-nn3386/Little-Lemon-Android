package com.example.littlelemon.ui.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.Markazi

@Composable
fun AppTextInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    required: Boolean = false,
    isError: Boolean = false,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    containerModifier: Modifier = Modifier
    ) {
    var hasBeenFocused by remember { mutableStateOf(false) }
    var showRequireError by remember { mutableStateOf(false) }

    val label = if (required) "$label (*)" else label

    Column(modifier = containerModifier) {
        // Label on top of input
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            isError = showRequireError || isError,
            value = value,
            onValueChange = {
                onValueChange(it)
                showRequireError = false // ✅ remove error when user types
            },
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        // Mark that the user has started interacting
                        hasBeenFocused = true
                    } else if (hasBeenFocused && required && value.isBlank()) {
                        // ✅ Only show error if it was focused at least once AND is now blurred/empty
                        showRequireError = true
                    }
                },
            placeholder = { Text(text = placeholder) },
            shape = RoundedCornerShape(8.dp), // Border radius 8
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,       // Changed to Green when focused
                unfocusedBorderColor = LightGray, // Remains LightGray when not focused
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                cursorColor = Green ,
                errorBorderColor = Color.Red,
                errorCursorColor = Color.Red// Optional: makes the cursor match the focus state
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
        // ✅ Error text with Animation
        AnimatedVisibility(
            visible = showRequireError && required,
            enter = expandVertically(animationSpec = tween(300)) + fadeIn(),
            exit = shrinkVertically(animationSpec = tween(300)) + fadeOut()
        ) {
            Text(
                text = "This field is required",
                color = Color.Red,
                fontFamily = Markazi,
                style = MaterialTheme.typography.bodyMedium, // Suggested for error labels
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTextInputPreview() {
    Column {
        AppTextInput("Test", "Test", {})
    }
}