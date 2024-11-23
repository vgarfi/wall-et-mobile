package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.cards.model.CreditCard

@Composable
fun EnterAmount(
    onAmountChange : MutableState<String>
): @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(R.string.enter_amount))
            AmountInputField(onValueChange = {onAmountChange.value = it})
        }
    }
}

@Composable
fun EnterPayment(
    cards: List<CreditCard>?,
    paymentBySelfBalance: Boolean = false,
    selectedObject: (CreditCard) -> Unit = {},
    amount: String = "0",
): @Composable () -> Unit {
    return {

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp
        val screenWidth = configuration.screenWidthDp

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().height((screenHeight*0.45).dp)
        ) {
            Text(text = stringResource(R.string.you_are_entering), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Box(Modifier.height(15.dp))
            Text("$" + amount, style = TextStyle(
                fontSize = 54.sp,
                color = MainPurple,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),)
                PaymentSelector(
                    selectedObject = selectedObject,
                    PaymentBySelfBalance = paymentBySelfBalance
                ) {
                    Text(text = stringResource(R.string.payment_method), fontWeight = FontWeight.Bold)
                }
        }
    }
}