package com.example.wall_etmobile.screens.cashflow

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R

@Composable
fun TransactionDetailsScreen(
    navController: NavController
) {
    Box (modifier = Modifier
        .fillMaxWidth()){
        Image(
            painter = painterResource(id = R.drawable.details_background),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),

        ) {
            TransactionDetails()
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}) {
                Text(text = "Compartir comprobante")
            }
            Button(onClick = { navController.popBackStack()}) {
                Text(text = "Volver al inicio")
            }
        }
    }
}