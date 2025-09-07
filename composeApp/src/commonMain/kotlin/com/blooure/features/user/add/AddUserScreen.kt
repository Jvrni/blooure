package com.blooure.features.user.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.add_user_button_label
import blooure.composeapp.generated.resources.add_user_name_label
import blooure.composeapp.generated.resources.add_user_title
import com.designsystem.components.TopAppBar
import com.designsystem.theme.Colors
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddUserScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Colors.background)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(stringResource(Res.string.add_user_title)) {

            }

            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = "",
                label = {
                    Text(text = stringResource(Res.string.add_user_name_label))
                },
                onValueChange = {

                }
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .align(Alignment.BottomCenter),
            onClick = {}
        ) {
            Text(text = stringResource(Res.string.add_user_button_label))
        }
    }
}