package com.example.wall_etmobile.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wall_etmobile.design_kit.shared.BaseScaffold

@Composable
fun CardsScreen() {
    BaseScaffold(tinyText = "tus", bigText = "Tarjetas") {
    }
}

@Preview
@Composable
fun CardsScreenPreview() {
    CardsScreen()
}