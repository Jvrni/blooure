package com.blooure.features.user.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.ic_delete
import blooure.composeapp.generated.resources.ic_plus
import blooure.composeapp.generated.resources.img_empty_user
import blooure.composeapp.generated.resources.user_list_empty_label
import blooure.composeapp.generated.resources.user_list_title
import com.blooure.features.user.list.contract.UserListContract
import com.designsystem.components.TopAppBar
import com.designsystem.theme.Colors
import com.designsystem.theme.Typography
import com.navigation.Destinations
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserListScreen(state: UserListContract.State, event: (UserListContract.Event) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.background,
        contentColor = Colors.onBackground,
        topBar = {
            TopAppBar(stringResource(Res.string.user_list_title)) {
                event.invoke(UserListContract.Event.OnBack)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                onClick = {
                    event.invoke(UserListContract.Event.OnNavigate(Destinations.AddUser))
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_plus),
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = paddingValues.calculateTopPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                if (state.users.isEmpty() && !state.showError)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            modifier = Modifier.size(350.dp).padding(top = 40.dp),
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

            item {
                if (state.showError)
                    Box(modifier = Modifier.fillMaxSize().background(Colors.background)) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Something went wrong",
                            style = Typography.bodyMedium.copy(fontSize = 16.sp),
                            color = Colors.error
                        )
                    }
            }

            itemsIndexed(state.users) { index, user ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(46.dp).weight(1f)
                            .shadow(2.dp, shape = RoundedCornerShape(24.dp))
                            .background(Colors.background, shape = RoundedCornerShape(24.dp))
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = user.name,
                            style = Typography.bodyMedium.copy(fontSize = 16.sp),
                            color = Colors.outline
                        )
                    }

                    Card(
                        modifier = Modifier.clickable {
                            event.invoke(UserListContract.Event.OnDelete(user))
                        },
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Icon(
                            modifier = Modifier.size(36.dp).padding(10.dp),
                            painter = painterResource(Res.drawable.ic_delete),
                            contentDescription = "",
                            tint = Colors.error
                        )
                    }
                }
            }
        }
    }
}