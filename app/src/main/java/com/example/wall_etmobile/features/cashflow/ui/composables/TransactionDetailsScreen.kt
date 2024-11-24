package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel

@Composable
fun TransactionDetailsScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    operationsViewModel : OperationsViewModel,
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
        LazyColumn (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),

        ) { item {   TransactionDetails(operationsViewModel = operationsViewModel) }
      //      item {
      //          ActionButton(title = "Compartir comprobante", enabled = false, onClick = { }, modifier = Modifier.padding(bottom = 12.dp), elevation = false)
      //      }
            item {
                ActionButton(title = "Volver al inicio", onClick = {
                    operationsViewModel.clearAll()
                    navigateToScreen("home", emptyMap()) })

            }
        }
    }
}