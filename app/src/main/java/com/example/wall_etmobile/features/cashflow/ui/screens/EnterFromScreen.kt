package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.features.cashflow.ui.composables.FromCardContent
import com.example.wall_etmobile.features.cashflow.ui.composables.WithCVUContent
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.transactions.model.TransactionType
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToLong

@Composable
fun EnterFromScreen (
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    source: String?,
    page: Int?,
    operationsViewModel: OperationsViewModel,
    userViewModel : AuthViewModel,
    transactionViewModel : TransactionViewModel,
) {
    userViewModel.getUserData()?.let { operationsViewModel.setReceiverID(it.email) }
    val headerText = when (source) {
        "bank" -> stringResource(R.string.from_another_bank_account)
        "card" -> stringResource(R.string.using_any_card)
        else -> stringResource(R.string.from_another_bank_account)
    }
    var onclick  = remember { mutableStateOf({navigateToScreen("enter", emptyMap())}) }
    if (source == "bank"){
        onclick.value = {navigateToScreen("enter", emptyMap())}
    }
    CashFlowBaseScaffold(bigText = ("$" + "%.2f".format(userViewModel.state.user?.wallet?.balance)), smallText = stringResource(R.string.home_mount_text), navController = navController, onArrowClick = onclick.value ) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            if (source == "card") {
                FromCardContent(onMethodChange = { onclick.value = it },
                    navigateToScreen = navigateToScreen,
                    operationsViewModel = operationsViewModel,
                    userViewModel = userViewModel,
                    transactionViewModel = transactionViewModel
                )
            } else {
                WithCVUContent(onClick = {navigateToScreen("home", emptyMap())})
            }
        }
    }
}