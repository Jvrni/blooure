package com.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import blooure.core.designsystem.generated.resources.Res
import blooure.core.designsystem.generated.resources.roboto_bold
import blooure.core.designsystem.generated.resources.roboto_light
import blooure.core.designsystem.generated.resources.roboto_medium
import blooure.core.designsystem.generated.resources.roboto_regular
import blooure.core.designsystem.generated.resources.roboto_semi_bold

@Composable
fun Typography(): Typography {
    val roboto = FontFamily(
        Font(Res.font.roboto_bold, FontWeight.Bold),
        Font(Res.font.roboto_semi_bold, FontWeight.SemiBold),
        Font(Res.font.roboto_medium, FontWeight.Medium),
        Font(Res.font.roboto_regular, FontWeight.Normal),
        Font(Res.font.roboto_light, FontWeight.Light)
    )

    return Typography(
        titleLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        titleMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodyLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodyMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodySmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        displaySmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        )
    )
}