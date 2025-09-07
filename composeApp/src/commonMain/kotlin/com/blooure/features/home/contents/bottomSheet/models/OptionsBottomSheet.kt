package com.blooure.features.home.contents.bottomSheet.models

import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.ic_document_center
import blooure.composeapp.generated.resources.ic_users
import blooure.composeapp.generated.resources.options_bottom_sheet_blood_pressure
import blooure.composeapp.generated.resources.options_bottom_sheet_users
import com.navigation.Destinations
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class OptionsBottomSheet(val icon: DrawableResource, val label: StringResource, val destination: Destinations) {
    BloodPressure(
        icon = Res.drawable.ic_document_center,
        label = Res.string.options_bottom_sheet_blood_pressure,
        destination = Destinations.BloodPressure
    ),
    User(
        icon = Res.drawable.ic_users,
        label = Res.string.options_bottom_sheet_users,
        destination = Destinations.UserList
    )
}
