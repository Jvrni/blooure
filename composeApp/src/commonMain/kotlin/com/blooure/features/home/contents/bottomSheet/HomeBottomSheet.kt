package com.blooure.features.home.contents.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.options_bottom_sheet_title
import com.blooure.features.home.contents.bottomSheet.models.OptionsBottomSheet
import com.designsystem.theme.Colors
import com.designsystem.theme.Typography
import com.navigation.Destinations
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onAction: (Destinations) -> Unit
) {
    val options = OptionsBottomSheet.entries.toTypedArray()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false,
            initialValue = SheetValue.Hidden,
            confirmValueChange = {
                if (it == SheetValue.Hidden) {
                    onDismiss.invoke()
                }
                true
            }
        )
    )

    LaunchedEffect(isVisible) {
        if (isVisible) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.hide()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxWidth(),
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContainerColor = Colors.background,
        sheetShadowElevation = 5.dp,
        sheetTonalElevation = 5.dp,
        sheetSwipeEnabled = true,
        sheetPeekHeight = 200.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).weight(1f),
                        text = stringResource(Res.string.options_bottom_sheet_title),
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }

                itemsIndexed(options) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAction.invoke(item.destination)
                            }
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
    ) {

    }
}