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
            verticalArrangement = Arrangement.Bottom
        ) {
            if (header.isNotEmpty()) {
                Text(text = header, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        CustomTextField(
                            hint = hint,
                            label = "",
                            isPassword = false,
                            controller = input,
                        )
                    }
                }
                ActionButton(
                    title = stringResource(R.string.continue_text),
                    onClick = onClick,
                    elevation = true,
                    modifier = Modifier.align(Alignment.End),
                    enabled = input.value.isNotEmpty()
                )
            }
        }
    }
}

@Composable
fun TransferAmount(
    onClick: () -> Unit = {},
    operationsViewModel: OperationsViewModel,
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
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize(),
    ) {
        ContactTransferTile(
            icon = logo,
            contactName = "",
            contactDetails = operationsViewModel.uiState.currentReceiverID ?: "",
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                item {
                    AmountInputField(onValueChange = { amountInput.value = it }, textIn = amountInput.value)
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
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
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                item {
                    CustomTextField(
                        hint = stringResource(R.string.write_a_message),
                        label = "",
                        isPassword = false,
                        controller = messageInput,
                    )
                }
            }
            ActionButton(
                title = stringResource(R.string.continue_text),
                onClick = onClick,
                elevation = true,
                enabled = amountInput.value.isNotEmpty() && amountInput.value.toDouble() > 0
            )
        }
    }
    }
}

@Composable
fun TransferPayment(
    onClick: () -> Unit = {},
    PaymentBySelfBalance: Boolean = false,
    buttonText: String = stringResource(R.string.transfer),
    operationsViewModel: OperationsViewModel,
    @DrawableRes logo : Int = R.drawable.logo
): @Composable () -> Unit {
    return {
        val paymentInput = remember { mutableStateOf<CreditCard?>(operationsViewModel.uiState.currentPaymentMethod) }

        LaunchedEffect(paymentInput.value) {
            paymentInput.value?.let { operationsViewModel.setPaymentMethod(it) }
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                operationsViewModel.uiState.currentReceiverID?.let {
                    ContactTransferTile(
                        icon = logo,
                        contactName = "",
                        contactDetails = it,
                    )
                }
            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Text(text = stringResource(R.string.you_are_transfering), fontWeight = FontWeight.Bold)
                }
                item {
                    Text(
                        "$" + operationsViewModel.uiState.currentAmount,
                        style = TextStyle(
                            fontSize = 54.sp,
                            color = MainPurple,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.message) + ": " + if (operationsViewModel.uiState.currentMessage?.isEmpty() == true) stringResource(R.string.no_message) else operationsViewModel.uiState.currentMessage,
                        fontWeight = FontWeight.Light
                    )
                }
                item {
                    Column {
                        PaymentSelector(
                            selectedObject = { paymentInput.value = it },
                            PaymentBySelfBalance = PaymentBySelfBalance
                        ) {
                            Text(text = stringResource(R.string.payment_method), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

}