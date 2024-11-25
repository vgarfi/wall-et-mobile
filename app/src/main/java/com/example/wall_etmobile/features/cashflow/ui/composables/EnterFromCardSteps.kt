package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel

@Composable
fun EnterAmount(
    operationsViewModel: OperationsViewModel,
    onClick: () -> Unit = {},
): @Composable () -> Unit {
    return {
       // operationsViewModel.setReceiverID()
        val amountInput = remember { mutableStateOf(operationsViewModel.uiState.currentAmount ?: "") }
        LaunchedEffect(amountInput.value) {
            operationsViewModel.setAmount(amountInput.value)
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().fillMaxHeight().verticalScroll(rememberScrollState())
        ) {
            Text(text = stringResource(R.string.enter_amount))
            Spacer(modifier = Modifier.weight(0.03f))
            AmountInputField(onValueChange = {amountInput.value = it})
            Spacer(modifier = Modifier.weight(1f))
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.continue_text),
                onClick = onClick,
                elevation = true,
                enabled = !operationsViewModel.uiState.currentAmount.isNullOrEmpty(),
            )
        }
    }
}

@Composable
fun EnterPayment(
    paymentBySelfBalance: Boolean = false,
    amount: String = "0",
    operationsViewModel: OperationsViewModel,
    onClick: () -> Unit = {},
): @Composable () -> Unit {
    return {
        val paymentInput = remember { mutableStateOf<CreditCard?>(operationsViewModel.uiState.currentPaymentMethod) }
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp
        LaunchedEffect(paymentInput.value) {
            paymentInput.value?.let { operationsViewModel.setPaymentMethod(it) }
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().fillMaxHeight().verticalScroll(rememberScrollState())
        ) {
            Text(text = stringResource(R.string.you_are_entering), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.weight(0.005f))
            Text("$" + amount, style = TextStyle(
                fontSize = 54.sp,
                color = MainPurple,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),)
            Spacer(modifier = Modifier.weight(0.4f))
                PaymentSelector(
                    selectedObject = {paymentInput.value = it},
                    PaymentBySelfBalance = paymentBySelfBalance,
                ) {
                    Text(text = stringResource(R.string.payment_method), fontWeight = FontWeight.Bold)
                }
            Spacer(modifier = Modifier.weight(0.1f))
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.enter),
                onClick = onClick,
                elevation = true,
                enabled = operationsViewModel.uiState.currentPaymentMethod != null,
            )
        }
    }
}