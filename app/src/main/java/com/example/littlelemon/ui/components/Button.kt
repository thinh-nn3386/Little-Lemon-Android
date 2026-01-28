package com.example.littlelemon.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Markazi
import com.example.littlelemon.ui.theme.Orange
import com.example.littlelemon.ui.theme.White
import com.example.littlelemon.ui.theme.Yellow

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    enabled: Boolean = true,
    label: String = "Button",
    onClick: () -> Unit = {},
) {
    // Define colors based on the active state
    val backgroundColor = if (isActive) Green else Yellow
    val textColor = if (isActive) White else Black
    val border = if (isActive) BorderStroke(1.dp, Green) else BorderStroke(1.dp, Yellow)

    Button(
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        border = if (enabled) border else null,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            fontFamily = Markazi,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    Column {
        AppButton()
        AppButton(isActive = true)
    }
}