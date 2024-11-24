package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun CashFlowBaseScaffold (
    bigText: String,
    navController: NavController,
    onArrowClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,

) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize().background(color = MainWhite)) {
        Image(
            painter = painterResource(id = R.drawable.cashflow_header),
            contentDescription = "header",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).padding(vertical = 20.dp)
        ) {
            CashFlowTitleRow(bigText = bigText, navController = navController,onArrowClick)
            content()
        }
    }

}