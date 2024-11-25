package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.designKit.CustomTextField
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel

@Composable
fun TransferTo(
    onClick: () -> Unit = {},
    hint: String = "",
    header: String = "",
    operationsViewModel: OperationsViewModel
) : @Composable () -> Unit {
    return {
        val input = remember { mutableStateOf(operationsViewModel.uiState.currentReceiverID ?: "") }
        LaunchedEffect(input.value) {
            operationsViewModel.setReceiverID(input.value)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(100.dp))
            Column {
                if (header.isNotEmpty()) {
                    Text(text = header, fontWeight = FontWeight.Bold)
                }
                CustomTextField(
                    hint = hint,
                    label = "",
                    isPassword = false,
                    controller = input,
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            ActionButton(
                title = stringResource(R.string.continue_text),
                onClick = onClick,
                elevation = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                enabled = input.value.isNotEmpty(),
            )

        }
    }
}

@Composable
fun TransferAmount(
    onClick: () -> Unit = {},
    operationsViewModel: OperationsViewModel,
    type: String,
    @DrawableRes logo : Int = R.drawable.logo
) : @Composable () -> Unit {
    return {
    val amountInput = remember { mutableStateOf(operationsViewModel.uiState.currentAmount ?: "") }
    val messageInput = remember { mutableStateOf(operationsViewModel.uiState.currentMessage ?: "") }

    LaunchedEffect(amountInput.value) {
        operationsViewModel.setAmount(amountInput.value)
    }
    LaunchedEffect(messageInput.value) {
        operationsViewModel.setDescription(messageInput.value)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.weight(0.15f))
        ContactTransferTile(
            icon = logo,
            contactName = operationsViewModel.uiState.currentReceiverID.orEmpty(),
            contactDetails = type,
        )
        Spacer(modifier = Modifier.weight(0.4f))

        AmountInputField(onValueChange = { amountInput.value = it }, textIn = amountInput.value)
        Spacer(modifier = Modifier.weight(0.15f))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = stringResource(R.string.message),
                    fontWeight = FontWeight.Bold
                )
                Text(text = " " + stringResource(R.string.optional))
                Spacer(modifier = Modifier.weight(0.1f))
            }
            CustomTextField(
                hint = stringResource(R.string.write_a_message),
                label = "",
                isPassword = false,
                controller = messageInput,
            )
        }

            Spacer(modifier = Modifier.weight(1f))
            ActionButton(
                title = stringResource(R.string.continue_text),
                onClick = onClick,
                elevation = true,
                enabled = amountInput.value.isNotEmpty() && amountInput.value.toDouble() > 0,
                modifier = Modifier.fillMaxWidth()
            )

    }
    }
}

@Composable
fun TransferPayment(
    onClick: () -> Unit = {},
    buttonText: String = stringResource(R.string.transfer),
    operationsViewModel: OperationsViewModel,
    type: String,
    userViewModel : AuthViewModel,
    @DrawableRes logo : Int = R.drawable.logo
): @Composable () -> Unit {
    return {
        val paymentInput = remember { mutableStateOf<CreditCard?>(operationsViewModel.uiState.currentPaymentMethod) }
        val allowSelfBalance = when {

            operationsViewModel.uiState.currentReceiverID == userViewModel.getUserData()?.email -> false

            operationsViewModel.uiState.currentAmount.isNullOrEmpty() -> false

            else -> {
                val currentAmount = operationsViewModel.uiState.currentAmount?.toDoubleOrNull()
                val walletBalance = userViewModel.getUserData()?.wallet?.balance
                currentAmount != null && walletBalance != null && currentAmount <= walletBalance
            }
        }


        LaunchedEffect(paymentInput.value) {
            paymentInput.value?.let { operationsViewModel.setPaymentMethod(it) }
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(0.15f))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                operationsViewModel.uiState.currentReceiverID?.let {
                    ContactTransferTile(
                        icon = logo,
                        contactName = operationsViewModel.uiState.currentReceiverID.orEmpty(),
                        contactDetails = type,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Text(text = stringResource(R.string.you_are_transfering), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(0.15f))
            Text(
                "$" + operationsViewModel.uiState.currentAmount,
                style = TextStyle(
                    fontSize = 55.sp,
                    color = MainPurple,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                ),
            )
            Spacer(modifier = Modifier.weight(0.15f))
            Text(
                text = stringResource(R.string.message) + ": " + if (operationsViewModel.uiState.currentMessage?.isEmpty() == true) stringResource(R.string.no_message) else operationsViewModel.uiState.currentMessage,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.weight(0.35f))

            PaymentSelector(
                selectedObject = { paymentInput.value = it },
                PaymentBySelfBalance = allowSelfBalance
            ) {
                Text(text = stringResource(R.string.payment_method), fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.weight(0.1f))

                ActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = buttonText,
                    onClick = onClick,
                    elevation = true,
                    enabled = operationsViewModel.uiState.currentPaymentMethod != null,
                )

        }
    }

}