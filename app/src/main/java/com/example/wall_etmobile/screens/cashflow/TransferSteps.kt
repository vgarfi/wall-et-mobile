package com.example.wall_etmobile.screens.cashflow

import CustomTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.ActionButton
fun TransferTo(
    topPadding: Int,
    onClick: () -> Unit = {},
    onValueChange: MutableState<String>,
    hint: String = "",
    header: String = ""
): @Composable () -> Unit {
    return {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

                if (header.isNotEmpty()) {
                    Text(text = header, fontWeight = FontWeight.Bold)
                }
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        CustomTextField(
                            hint = hint,
                            label = "",
                            isPassword = false,
                            controller = onValueChange,
                        )
                    }
                }
                ActionButton(
                    title = "Continuar",
                onClick = onClick,
                elevation = true,
                modifier = Modifier.align(Alignment.End)
                )
            }

        }
    }
}

fun TransferAmount(
    topPadding: Int,
    onClick: () -> Unit = {},
    onValueChange: MutableState<String>,
    contactName: String = "Tomas",
    contactDetails: String = "bordatomas@hotmail.com"
): @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize(),
        ) {

                ContactTransferTile(
                    icon = R.drawable.logo,
                    contactName = contactName,
                    contactDetails = contactDetails,
                )


            Text(text = "Mensaje (opcional)", fontWeight = FontWeight.Bold)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
                  modifier = Modifier.weight(1f)) {
            item {
                CustomTextField(
                    hint = "",
                    label = "",
                    isPassword = false,
                    controller = onValueChange,
                )
            }
            }
            ActionButton(
                title = "Continuar",
                onClick = onClick,
                elevation = true
            )
        }
        }
    }
}

fun TransferPayment(
    topPadding: Int,
    onClick: () -> Unit = {},
    onValueChange: MutableState<String>,
    contactName: String = "Tomas",
    contactDetails: String = "bordatomas@hotmail.com",
    message: String = "Asado",
    cardsInfo: List<CreditCardInfo> = listOf(
        CreditCardInfo(
            bankName = "",
            cardNumber = 0,
            cardHolder = "",
            cardExpiration = "",
            cardImage = 0
        )
    ),
    PaymentBySelfBalance: Boolean = false,
    selectedObject: (CreditCardInfo) -> Unit = {},
    buttonText: String = "Transferir",
    amount: Int = 0,
    buttonEnabled: Boolean = true
): @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize(),
        ) {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                item {
                    ContactTransferTile(
                        icon = R.drawable.logo,
                        contactName = contactName,
                        contactDetails = contactDetails,
                    )
                }
                item {
                    Text(text = "Vas a transferirle", fontWeight = FontWeight.Bold)
                }
                item {
                    CustomTextField(
                        hint = "",
                        label = "",
                        isPassword = false,
                        controller = onValueChange,
                    )
                }
                item {
                    Text(text = "Mensaje : $message", fontWeight = FontWeight.Light)
                }
                item {
                    Column(modifier = Modifier.heightIn(max = 240.dp)) {
                        PaymentSelector(
                            cardsInfo = cardsInfo,
                            selectedObject = selectedObject,
                            PaymentBySelfBalance = PaymentBySelfBalance
                        ) {
                            Text(text = "Medio de pago", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            ActionButton(
                title = buttonText,
                onClick = onClick,
                elevation = true,
                enabled = buttonEnabled,
            )
        }
    }
}
