package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun SectionTitle (
    tinyText: String,
    bigText: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = tinyText, color = MainWhite, fontSize = 20.sp)
        Box(modifier = Modifier.height(3.dp))
        Text(text = bigText, color = MainWhite, fontSize = 31.sp, fontWeight = FontWeight.Bold)

    }
}

@Preview
@Composable
fun SectionTitlePreivew() {
    SectionTitle("tus", "Tarjetas")
}