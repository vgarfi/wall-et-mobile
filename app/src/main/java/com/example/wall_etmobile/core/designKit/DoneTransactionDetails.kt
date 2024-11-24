package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainPurple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cashflow.ui.composables.ContactTransferTile
import com.example.wall_etmobile.features.transactions.model.TransactionInfo
import com.example.wall_etmobile.features.transactions.model.TransactionType

@Composable
fun DoneTransactionDetails(
    transactionInfo: TransactionInfo,
) {
    val scrollState = rememberScrollState()

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface (
            color = MainWhite,
        ){
            Column (
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.successful_transference),
                    color = MainGreen,
                    fontWeight = FontWeight.W800,
                    fontSize = 19.sp
                )
                Text(
                    text = "$ " + transactionInfo.amount.toString(),
                    color = MainPurple,
                    fontWeight = FontWeight.W900,
                    fontSize = 55.sp
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = when(transactionInfo.type){
                        TransactionType.INCOME, TransactionType.CHARGE -> stringResource(R.string.received) + " " + stringResource(R.string.from)
                        TransactionType.TRANSFER -> stringResource(R.string.sent) + " " + stringResource(R.string.to)
                    },
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                ContactTransferTile(
                    icon = R.drawable.logo,
                    contactName = when(transactionInfo.type) {
                        TransactionType.INCOME, TransactionType.CHARGE -> transactionInfo.payer?.lastName + ", " + transactionInfo.payer?.firstName
                        TransactionType.TRANSFER -> transactionInfo.receiver?.lastName + ", " + transactionInfo.receiver?.firstName
                    },
                    contactDetails = "ISBC"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(
                        text = "${stringResource(R.string.message)}:",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " ${stringResource(R.string.rent)}",
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = stringResource(R.string.transaction_details),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(18.dp))

                TransactionDetailItem(
                    label = stringResource(R.string.payment_method),
                    value = when(transactionInfo.payType){
                        "CARD" -> stringResource(R.string.card)
                        else -> stringResource(R.string.money_in_wallet)
                    }
                )
                transactionInfo.updatedAt?.let { TransactionDetailItem(label = stringResource(R.string.date), value = it) }
                TransactionDetailItem(label = stringResource(R.string.reference), value = transactionInfo.id.toString())

                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.total_amount),
                        color = MainPurple,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold)
                    Text(
                        text = "$ " + transactionInfo.amount.toString(),
                        fontSize = 24.sp,
                        color = MainPurple, // Purple color
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }
    }

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