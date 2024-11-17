package com.example.wall_etmobile.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.pathak.barberapp.navigation.Screen

@Composable
fun TransactionsScreen() {
    BaseScaffold(tinyText = "tus", bigText = "Movimientos") {

    }

}

@Preview
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen()
}