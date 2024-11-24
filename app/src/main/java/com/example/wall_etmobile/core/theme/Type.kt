package com.example.wall_etmobile.core.theme

import android.R.attr.fontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R


object AppFont {
    val WalletFont = FontFamily(
        Font(R.font.ps_regular),
        Font(R.font.ps_black),
        Font(R.font.ps_black_italic),
        Font(R.font.ps_bold),
        Font(R.font.ps_bold_italic),
        Font(R.font.ps_italic),
        Font(R.font.ps_light),
        Font(R.font.ps_thin),
    )
}

private val walletTypography = Typography()
val Typography = Typography(
    displayLarge = walletTypography.displayLarge.copy(fontFamily = AppFont.WalletFont),
    displayMedium = walletTypography.displayMedium.copy(fontFamily = AppFont.WalletFont),
    displaySmall = walletTypography.displaySmall.copy(fontFamily = AppFont.WalletFont),

    headlineLarge = walletTypography.headlineLarge.copy(fontFamily = AppFont.WalletFont),
    headlineMedium = walletTypography.headlineMedium.copy(fontFamily = AppFont.WalletFont),
    headlineSmall = walletTypography.headlineSmall.copy(fontFamily = AppFont.WalletFont),

    titleLarge = walletTypography.titleLarge.copy(fontFamily = AppFont.WalletFont),
    titleMedium = walletTypography.titleMedium.copy(fontFamily = AppFont.WalletFont),
    titleSmall = walletTypography.titleSmall.copy(fontFamily = AppFont.WalletFont),

    bodyLarge = walletTypography.bodyLarge.copy(fontFamily = AppFont.WalletFont),
    bodyMedium = walletTypography.bodyMedium.copy(fontFamily = AppFont.WalletFont),
    bodySmall = walletTypography.bodySmall.copy(fontFamily = AppFont.WalletFont),

    labelLarge = walletTypography.labelLarge.copy(fontFamily = AppFont.WalletFont),
    labelMedium = walletTypography.labelMedium.copy(fontFamily = AppFont.WalletFont),
    labelSmall = walletTypography.labelSmall.copy(fontFamily = AppFont.WalletFont)
)
//
//// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.ExtraBold,
//        fontSize = 50.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleMedium = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Bold,
//        fontSize = 35.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Bold,
//        fontSize = 22.sp,
//        lineHeight = 25.sp,
//        letterSpacing = 0.sp
//    )
//    /* Other default text styles to override
//
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//)