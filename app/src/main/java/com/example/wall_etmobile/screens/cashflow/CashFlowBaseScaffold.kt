package com.example.wall_etmobile.screens.cashflow

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.TitleRow
import com.example.wall_etmobile.ui.theme.MainWhite


@Composable
fun CashFlowBaseScaffold (
    bigText: String,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().background(color = MainWhite)) {
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
                .padding(16.dp)
        ) {
            CashFlowTitleRow(bigText = bigText, navController = navController)
            content()
        }
    }
}