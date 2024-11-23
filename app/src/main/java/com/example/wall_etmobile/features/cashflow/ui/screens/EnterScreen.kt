package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ExtendedBigIconButton
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold

@Composable
fun EnterScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit
) {
    CashFlowBaseScaffold(bigText = stringResource(R.string.enter_money), navController = navController, onArrowClick = {navigateToScreen("home", emptyMap())}) {
        Column (
            modifier = Modifier
                .padding(all = 12.dp)
                .padding(top = 80.dp)
        ){
            Text(text = stringResource(R.string.choose_any_option), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(30.dp))
            ExtendedBigIconButton(
                icon = R.drawable.piggy,
                title = stringResource(R.string.from_another_bank_account),
                subtitle = stringResource(R.string.making_a_transfer),
                onClick = {
                    navigateToScreen("enterFrom", mapOf("source" to "bank","page" to "0"))
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            ExtendedBigIconButton(
                icon = R.drawable.card,
                title = stringResource(R.string.with_debit_card),
                subtitle = stringResource(R.string.using_any_card),
                onClick = {
                    navigateToScreen("enterFrom", mapOf("source" to "card","page" to "0"))
                }
            )
        }
    }

}