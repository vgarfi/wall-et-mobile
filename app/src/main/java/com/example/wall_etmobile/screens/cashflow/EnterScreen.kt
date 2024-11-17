package com.example.wall_etmobile.screens.cashflow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.wall_etmobile.design_kit.shared.BaseScaffold

@Composable
fun EnterScreen(
    navController: NavController
) {
    CashFlowBaseScaffold(bigText = "Ingresá dinero", navController = navController) {

    }

}