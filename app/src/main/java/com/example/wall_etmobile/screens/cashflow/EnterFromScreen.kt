package com.example.wall_etmobile.screens.cashflow

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun EnterFromScreen (
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    source: String?,
    page: Int?,
) {
    Text(text = "ola")
}