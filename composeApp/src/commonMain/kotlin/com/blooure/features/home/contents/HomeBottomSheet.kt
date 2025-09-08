package com.blooure.features.home.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.home_options_bottom_sheet_title
import com.blooure.features.home.models.OptionsBottomSheet
import com.designsystem.theme.Colors
import com.designsystem.theme.Typography
import com.navigation.Destinations
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Composable function that displays a modal bottom sheet with a list of options.
 *
 * This bottom sheet is used to present various actions or navigation choices to the user.
 * It includes a title and a list of items, each with an icon and a label.
 * Clicking on an item triggers the `onAction` callback with the corresponding destination.
 *
 * @param onDismiss A lambda function to be invoked when the bottom sheet is dismissed
 *                  (e.g., by swiping down or tapping outside).
 * @param onAction A lambda function that takes a [Destinations] object as a parameter.
 *                 This function is invoked when an option in the bottom sheet is clicked.
 *                 The [Destinations] object represents the target screen or action.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    onDismiss: () -> Unit,
    onAction: (Destinations) -> Unit
) {
    val options = OptionsBottomSheet.entries.toTypedArray()
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        containerColor = Colors.background,
        sheetState = bottomSheetState,
        onDismissRequest = { onDismiss.invoke() },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).weight(1f),
                        text = stringResource(Res.string.home_options_bottom_sheet_title),
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }

                itemsIndexed(options) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onAction.invoke(item.destination) }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = "",
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 8.dp),
                            text = stringResource(item.label),
                            style = Typography.bodyMedium,
                        )
                    }

                    if (index != options.lastIndex)
                        HorizontalDivider()
                }
            }
        }
    )
}