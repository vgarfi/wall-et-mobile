package com.example.wall_etmobile.features.home.ui.designKit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun BackgroundIcon (
    icon: Int
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFFF2EAFF))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "boton",
        )
    }
}