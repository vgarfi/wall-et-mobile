package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.features.cashflow.ui.composables.FromCardContent
import com.example.wall_etmobile.features.cashflow.ui.composables.WithCVUContent

@Composable
fun EnterFromScreen (
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    source: String?,
    page: Int?,
) {
    val headerText = when (source) {
        "bank" -> "Desde otra cuenta bancaria"
        "card" -> "Con tarjeta de débito"
        else -> "Desde otra cuenta"
    }

    CashFlowBaseScaffold(bigText = headerText , navController = navController) {
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            if (source == "card") {
                FromCardContent()
            } else {
                WithCVUContent()
            }
        }
    }
}