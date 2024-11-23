package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.input.TextFieldValue
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainRed

@Composable
fun ExpirationDatePicker(
    expirationDate: String,
    onDateSelected: (String) -> Unit
) {
    var isError by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }

    val months = (1..12).map { it.toString().padStart(2, '0') }
    val years = (2024..2034).map { it.toString().substring(2) }
    var dateText by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(selectedMonth, selectedYear) {
        if (selectedMonth.isNotEmpty() && selectedYear.isNotEmpty()) {
            dateText = TextFieldValue("$selectedMonth/$selectedYear")
        }
    }

    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var cardNumberError by remember { mutableStateOf(false) }
    var expirationDate by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.clickable {
        if (selectedMonth.isEmpty()) {
            expandedMonth = true
        } else if (selectedYear.isEmpty()) {
            expandedYear = true
        }
    })
    OutlinedTextField(
        value = dateText,
        onValueChange = {
            if (selectedMonth.isEmpty()) {
                expandedMonth = true
            } else if (selectedYear.isEmpty()) {
                expandedYear = true
            }
        },
        label = {
            Text(if (isError) stringResource(R.string.error_invalid_date) else stringResource(R.string.expiration_date))
        },
        isError = isError,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = stringResource(R.string.select_date),
                modifier = Modifier.size(24.dp)
            )
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
        placeholder = { Text("MM/YY") },

        modifier = Modifier
            .clickable {
                expandedMonth = true
                expandedYear = true
            }
            .fillMaxWidth()
    )

    if (expandedMonth) {
        Dialog(
            onDismissRequest = { expandedMonth = false }
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.select_month), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    months.forEach { month ->
                        TextButton(
                            onClick = {
                                selectedMonth = month
                                expandedMonth = false
                                expandedYear = true
                            }
                        ) {
                            Text(text = month)
                        }
                    }
                }
            }
        }
    }

    if (expandedYear) {
        Dialog(
            onDismissRequest = { expandedYear = false },
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.select_year), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    years.forEach { year ->
                        TextButton(
                            onClick = {
                                selectedYear = year
                                expandedYear = false
                                onDateSelected("$selectedMonth/$selectedYear")
                            }
                        ) {
                            Text(text = year)
                        }
                    }
                }
            }
        }
    }

    if (isError) {
        Text(
            text = stringResource(R.string.error_invalid_date),
            color = MainRed,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}