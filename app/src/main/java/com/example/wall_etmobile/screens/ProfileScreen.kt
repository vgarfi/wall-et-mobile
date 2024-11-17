package com.example.wall_etmobile.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wall_etmobile.design_kit.shared.BaseScaffold

@Composable
fun ProfileScreen() {
    BaseScaffold(tinyText = "tu", bigText = "Perfil") {
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}