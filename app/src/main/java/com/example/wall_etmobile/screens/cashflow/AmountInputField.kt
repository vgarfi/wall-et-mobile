package com.example.wall_etmobile.screens.cashflow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.ui.theme.MainBlack
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainRed

@Composable
fun AmountInputField(
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue ->
            val sanitizedValue = newValue.filter { it.isDigit() || it == '.'}
            if (sanitizedValue.count { it == '.' } <= 1) {
                text = sanitizedValue
                onValueChange(sanitizedValue)
            }
        },
        textStyle = TextStyle(
            fontSize = 48.sp,
            color = MainBlack,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        prefix = {
            if (text.isNotEmpty()) Text(text = "$", style = TextStyle(
                fontSize = 48.sp,
                color = MainBlack,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),)
        },
        placeholder = {
            Text(
                text = "$ 00.00",
                color = Color.LightGray,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            cursorColor = MainPurple,
            focusedContainerColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
