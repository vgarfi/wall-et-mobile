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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun TransactionDetails() {
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
                    text = "Transferencia exitosa",
                    color = MainGreen,
                    fontWeight = FontWeight.W800,
                    fontSize = 19.sp
                )
                Text(
                    text = "La transacción se hizo correctamente",
                    color = MainGrey,
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "$7,500.00",
                    color = MainPurple,
                    fontWeight = FontWeight.W900,
                    fontSize = 55.sp
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "Enviado a",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                ContactTransferTile(icon = R.drawable.logo, contactName = "Agustín Ronda", contactDetails = "ISBC")
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(
                        text = "Mensaje:",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " cochera mes Agosto",
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = "Detalles de la transacción",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(18.dp))

                TransactionDetailItem(label = "Medio de pago", value = "Dinero en cuenta")
                TransactionDetailItem(label = "Fecha", value = "Agosto 31, 2024")
                TransactionDetailItem(label = "Hora", value = "20:32")
                TransactionDetailItem(label = "Referencia", value = "QOIU-0012-ADFE-2234")

                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Pago total",
                        color = MainPurple,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold)
                    Text(
                        text = "$7,500.00",
                        fontSize = 24.sp,
                        color = MainPurple, // Purple color
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
