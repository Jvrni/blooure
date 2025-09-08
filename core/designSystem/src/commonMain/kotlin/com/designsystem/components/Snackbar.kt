package com.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.designsystem.theme.Colors
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun Snackbar(
    modifier: Modifier,
    message: String,
    snackbarOptions: SnackbarOptions?,
    actionAfterDelay: () -> Unit
) {
    val color = when (snackbarOptions) {
        SnackbarOptions.Warning, SnackbarOptions.Error -> Colors.error
        else -> Colors.primary
    }

    if (snackbarOptions != null)
        Snackbar(
            modifier = modifier,
            containerColor = color,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message,
                textAlign = TextAlign.Center
            )
        }

    LaunchedEffect(snackbarOptions) {
        delay(1000)
        actionAfterDelay.invoke()
    }
}

enum class SnackbarOptions() {
    Success,
    Error,
    Warning,
}