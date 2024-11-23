package com.example.wall_etmobile.features.cards.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.DialogProperties
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.designKit.ExpirationDatePicker
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainRed
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun AddNewCreditCard(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddCard: (String, String, String, String) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
            ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MainWhite,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.card_info),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W800,
                        textAlign = TextAlign.Center,
                        color = MainPurple
                    )
                    Box(modifier = Modifier.height(22.dp))

                    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
                    var cardCVV by remember { mutableStateOf(TextFieldValue("")) }
                    var cardHolder by remember { mutableStateOf(TextFieldValue("")) }

                    var cardNumberError by remember { mutableStateOf(false) }
                    var cardCVVError by remember { mutableStateOf(false) }
                    var cardHolderError by remember { mutableStateOf(false) }

                    var expirationDate by remember { mutableStateOf("") }
                    var bankName by remember { mutableStateOf("")}

                    LazyColumn {
                        item {
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                OutlinedTextField(
                                    value = cardNumber,
                                    maxLines = 1,

                                    textStyle = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    onValueChange = {
                                        cardNumber = it
                                        cardNumberError = cardNumber.text.length != 16 || !cardNumber.text.all { char -> char.isDigit() }
                                        bankName = getBankFromCard(cardNumber.text)
                                    },
                                    label = {
                                        Text(stringResource(R.string.card_number), maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 14.sp)
                                    },
                                    isError = cardNumberError,
                                    leadingIcon = {
                                        getCardIcon(cardNumber = cardNumber.text).invoke()
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = if (cardNumberError) MainRed else MainPurple,
                                        cursorColor = if (cardNumberError) MainRed else MainPurple,
                                        errorBorderColor = MainRed,
                                    ),
                                    trailingIcon = {
                                        if (!cardNumberError && cardNumber.text.isNotEmpty()) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "validation",
                                                tint = MainGreen,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(0.7f)
                                )
                                Box(modifier = Modifier.width(6.dp))
                                OutlinedTextField(
                                    value = cardCVV,
                                    maxLines = 1,
                                    textStyle = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    onValueChange = {
                                        cardCVV = it
                                        cardCVVError =
                                            cardCVV.text.length != 3 || !cardCVV.text.all { char -> char.isDigit() }
                                    },
                                    label = {
                                        Text("CVV", maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)
                                    },
                                    isError = cardCVVError,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = if (cardCVVError) MainRed else MainPurple,
                                        cursorColor = if (cardCVVError) MainRed else MainPurple,
                                        errorBorderColor = MainRed,
                                    ),
                                    trailingIcon = {
                                        if (!cardCVVError && cardCVV.text.isNotEmpty()) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "validation",
                                                tint = MainGreen,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        item {
                            Box(modifier = Modifier.height(16.dp))
                        }
                        item {
                            ExpirationDatePicker(
                                expirationDate = expirationDate,
                                onDateSelected = { date ->
                                    expirationDate = date
                                }
                            )
                        }
                        item { Box(modifier = Modifier.height(16.dp)) }
                        item {
                            OutlinedTextField(
                                value = cardHolder,
                                maxLines = 1,
                                onValueChange = {
                                    if (it.text.length <= 17) {
                                        cardHolder = it
                                        cardHolderError = cardHolder.text.isEmpty()
                                    }
                                    cardHolderError = cardHolder.text.isEmpty()
                                },
                                label = {
                                    Text(stringResource(R.string.card_holder))
                                },
                                isError = cardHolderError,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "holder"
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = if (cardHolderError) MainRed else MainPurple,
                                    cursorColor = if (cardHolderError) MainRed else MainPurple,
                                    errorBorderColor = MainRed,
                                ),
                                trailingIcon = {
                                    if (!cardHolderError && cardHolder.text.isNotEmpty()) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "validation",
                                            tint = MainGreen,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item { Box(modifier = Modifier.height(26.dp)) }
                        item { Text(stringResource(R.string.your_new_card), color = MainGrey, fontWeight = FontWeight.W500) }
                        item { Box(modifier = Modifier.height(6.dp)) }
                        item {
                            CreditCardComponent(
                                bankName = bankName,
                                cardNumber = cardNumber.text,
                                cardHolder = cardHolder.text,
                                cardExpiration = expirationDate,
                                cardImageIndex = if (isValidCardNumber(cardNumber.text)) cardNumber.text.toLong().rem(6).toInt() else 0
                            )
                        }
                    }

                    ActionButton(
                        title = stringResource(R.string.add_card),
                        elevation = true,
                        onClick = {
                            onAddCard(
                                cardNumber.text,
                                cardCVV.text,
                                expirationDate,
                                cardHolder.text
                            )
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = (!cardHolderError && cardHolder.text.isNotEmpty()) && (!cardNumberError && cardNumber.text.isNotEmpty()) && (!cardCVVError && cardCVV.text.isNotEmpty())
                    )
                }
            }
        }
    }
}
