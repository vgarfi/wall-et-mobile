package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DashedCreditCard() {
    Box(modifier = Modifier.size((200 * 1.618).dp, 200.dp)) {
        CustomCard(
            isDashed = true,
            borderWidth = 2.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "AGREGAR NUEVA TARJETA",
                    color = Color(0xFF561F9B),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.size(40.dp).align(Alignment.CenterHorizontally)) {
                    CustomCard (
                        contentPadding = 8.dp,
                    ) {
                        Icon (
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color(0xFF561F9B),
                            modifier = Modifier.size(30.dp, 30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun DashedCreditCardPreview() {
    DashedCreditCard()
}
