package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.features.cashflow.ui.composables.AmountInputField
import com.example.wall_etmobile.features.cashflow.ui.composables.CreditCardInfo
import com.example.wall_etmobile.features.cashflow.ui.composables.PaymentSelector

fun EnterAmount(
    onAmountChange : MutableState<String>
): @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = "Ingresá el monto")
            AmountInputField(onValueChange = {onAmountChange.value = it})
        }
    }
}

fun EnterPayment(
    cardsInfo: List<CreditCardInfo> = listOf(
        CreditCardInfo(
            bankName = "",
            cardNumber = 0,
            cardHolder = "",
            cardExpiration = "",
            cardImage = 0
        )
    ),
    paymentBySelfBalance: Boolean = false,
    selectedObject: (CreditCardInfo) -> Unit = {},
    amount: String = "0",
): @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Text(text = "Vas a ingresarte", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                }
                item {
                    AmountInputField(readOnly = true, textIn = amount, onValueChange = {})
                }
                item {
                    Column(modifier = Modifier.heightIn(max = 240.dp)) {
                        PaymentSelector(
                            cardsInfo = cardsInfo,
                            selectedObject = selectedObject,
                            PaymentBySelfBalance = paymentBySelfBalance
                        ) {
                            Text(text = "Medio de pago", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}