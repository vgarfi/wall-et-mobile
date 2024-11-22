package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedImageWithText(
    size: Dp,
    painter: Painter? = null,
    title: String = "User",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedImage(size = size, painter = painter)
        Text(text = title, modifier = Modifier.padding(4.dp))
    }
}