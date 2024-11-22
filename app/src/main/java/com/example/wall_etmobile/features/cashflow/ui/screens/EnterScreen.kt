package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.ExtendedBigIconButton
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold

@Composable
fun EnterScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit
) {
    CashFlowBaseScaffold(bigText = "Ingresá dinero", navController = navController) {
        Column (
            modifier = Modifier
                .padding(all = 12.dp)
                .padding(top = 80.dp)
        ){
            Text(text = "Seleccioná alguna opción", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(30.dp))
            ExtendedBigIconButton(
                icon = R.drawable.piggy,
                title = "Desde otra cuenta bancaria",
                subtitle = "Realizando una transferencia",
                onClick = {
                    navigateToScreen("enterFrom", mapOf("source" to "bank","page" to "0"))
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            ExtendedBigIconButton(
                icon = R.drawable.card,
                title = "Con tarjeta de débito",
                subtitle = "Utilizando alguna de tus tarjetas",
                onClick = {
                    navigateToScreen("enterFrom", mapOf("source" to "card","page" to "0"))
                }
            )
        }
    }

}