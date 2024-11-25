package com.example.wall_etmobile.ui.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

data class RoundedImageData(
    val painter: Painter?,
    val title: String,
    val size: Dp,
    val fullname: String = "",
    val contact: String = ""
)
