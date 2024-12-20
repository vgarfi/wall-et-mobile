package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainPurple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.transactions.model.TransactionType
import java.sql.Date
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@Composable
fun TransactionDetails(
    operationsViewModel: OperationsViewModel,
) {
    val operation = when(operationsViewModel.uiState.operationType){
        TransactionType.TRANSFER -> stringResource(R.string.operationTransferSucces)
        TransactionType.INCOME -> stringResource(R.string.operationIncomeSuccess)
        else -> stringResource(R.string.operationTransferSucces)
    }
    val paymentMethod =
        if (operationsViewModel.uiState.currentPaymentMethod?.number == "0000 0000 0000 0000"){
            stringResource(R.string.money_in_wallet)
        }
        else{
            stringResource(R.string.card)
        }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(16.dp, top = 40.dp)
    ) {
        Surface (
            color = MainWhite,
            modifier = Modifier.padding(top = 36.dp),
            shape = RoundedCornerShape(16.dp)
        ){
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.height(36.dp))
                Text(
                    text = operation,
                    color = MainGreen,
                    fontWeight = FontWeight.W800,
                    fontSize = 19.sp
                )
                Text(
                    text = stringResource(R.string.transaction_succesfully_made),
                    color = MainGrey,
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = ("$" + operationsViewModel.uiState.currentAmount),
                    color = MainPurple,
                    fontWeight = FontWeight.W900,
                    fontSize = 55.sp
                )


                if (operationsViewModel.uiState.operationType == TransactionType.TRANSFER)
                {
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = stringResource(R.string.sent_to),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    ContactTransferTile(icon = R.drawable.logo, contactName = operationsViewModel.uiState.currentReceiverID ?: "", contactDetails = stringResource(R.string.wallet_account))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.message) + ": ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (operationsViewModel.uiState.currentMessage.isNullOrEmpty()) stringResource(R.string.no_message) else operationsViewModel.uiState.currentMessage!!,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = stringResource(R.string.transaction_details),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(18.dp))

                TransactionDetailItem(label = stringResource(R.string.payment_method), value = paymentMethod)
                TransactionDetailItem(label = stringResource(R.string.date), value = LocalDate.now().toString())
                TransactionDetailItem(label = stringResource(R.string.hour), value = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                TransactionDetailItem(label = stringResource(R.string.reference), value = generateReference())

                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.total_pay),
                        color = MainPurple,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold)
                    Text(
                        text = ("$" + operationsViewModel.uiState.currentAmount),
                        fontSize = 24.sp,
                        color = MainPurple,
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(MainWhite),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.check_circle),
                contentDescription = "icono",
                tint = MainGreen,
                modifier = Modifier.padding(5.dp).fillMaxWidth()
            )
        }
    }

}

fun generateReference(): String {
    fun randomSegment(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..length)
            .map { chars[Random.nextInt(chars.length)] }
            .joinToString("")
    }

    val segment1 = randomSegment(4)
    val segment2 = randomSegment(4)
    val segment3 = randomSegment(4)
    val segment4 = randomSegment(4)

    return "$segment1-$segment2-$segment3-$segment4"
}

@Composable
fun TransactionDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}
