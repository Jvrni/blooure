package com.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.designsystem.theme.Colors
import kotlinx.coroutines.delay

@Composable
fun Snackbar(
    modifier: Modifier,
    showSnackbar: Boolean,
    containerColor: Color = Colors.primary,
    message: String,
    actionAfterDelay: () -> Unit
) {

    if (showSnackbar)
        Snackbar(
            modifier = modifier,
            containerColor = containerColor,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = message)
        }

    LaunchedEffect(showSnackbar) {
        delay(1000)
        actionAfterDelay.invoke()
    }
}