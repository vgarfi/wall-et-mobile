package com.example.wall_etmobile.screens.cashflow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.example.wall_etmobile.design_kit.shared.ExtendedBigIconButton

@Composable
fun EnterScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit
) {
    CashFlowBaseScaffold(bigText = "Ingresá dinero", navController = navController) {
        ExtendedBigIconButton(
            icon = R.drawable.piggy,
            title = "Desde otra cuenta bancaria",
            subtitle = "Realizando una transferencia",
            onClick = {
                navigateToScreen("enterFrom", mapOf("source" to "account","page" to "0"))
            }
        )

        ExtendedBigIconButton(
            icon = R.drawable.bank,
            title = "Con tarjeta de deébito",
            subtitle = "Utilizando alguna de tus tarjetas",
            onClick = {
                navigateToScreen("enterFrom", mapOf("source" to "card","page" to "0"))
            }
        )
    }

}