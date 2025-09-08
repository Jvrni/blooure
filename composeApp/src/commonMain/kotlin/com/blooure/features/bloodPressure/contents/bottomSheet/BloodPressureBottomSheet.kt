package com.blooure.features.bloodPressure.contents.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.blood_pressure_bottom_sheet_title
import blooure.composeapp.generated.resources.img_empty_user
import blooure.composeapp.generated.resources.home_options_bottom_sheet_title
import blooure.composeapp.generated.resources.user_list_empty_label
import com.designsystem.theme.Colors
import com.designsystem.theme.Typography
import com.domain.models.User
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Composable function that displays a bottom sheet for selecting a user to record blood pressure.
 *
 * @param users List of users to display in the bottom sheet.
 * @param onDismiss Callback function to be invoked when the bottom sheet is dismissed.
 * @param onAction Callback function to be invoked when a user is selected from the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodPressureBottomSheet(
    users: List<User>,
    onDismiss: () -> Unit,
    onAction: (User) -> Unit
) {
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
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        text = stringResource(Res.string.blood_pressure_bottom_sheet_title),
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    if (users.isEmpty())
                        Column(
                            modifier = Modifier.fillMaxSize().padding(bottom = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                modifier = Modifier.size(300.dp),
                                painter = painterResource(Res.drawable.img_empty_user),
                                contentDescription = "",
                            )

                            Text(
                                text = stringResource(Res.string.user_list_empty_label),
                                style = Typography.bodyMedium.copy(fontSize = 16.sp),
                                color = Colors.outline
                            )
                        }
                }

                itemsIndexed(users) { index, user ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(46.dp)
                            .weight(1f)
                            .shadow(2.dp, shape = RoundedCornerShape(24.dp))
                            .background(Colors.background, shape = RoundedCornerShape(24.dp))
                            .padding(horizontal = 12.dp)
                            .clickable {
                                onAction.invoke(user)
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = user.name,
                            style = Typography.bodyMedium.copy(fontSize = 16.sp),
                            color = Colors.outline
                        )
                    }
                }
            }
        }
    )
}