package com.example.wall_etmobile.screens.cashflow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.design_kit.shared.BaseScaffold

@Composable
fun ChargeScreen(
    navController: NavController
) {
    CashFlowBaseScaffold(bigText = "Cobrar", navController = navController) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 80.dp)
        ) {
            var amount by remember { mutableStateOf("") }

            Text(text = "Quiero cobrar")
            AmountInputField(
                onValueChange =  { newAmount ->
                    amount = newAmount
                }
            )
        }
    }

}