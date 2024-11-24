package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.core.theme.MainPurple

@Composable
fun CircularAddButton(
    size: Dp,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Transparent, CircleShape)
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawCircle(
                    color = MainPurple,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
                    )
                )
            }
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "pointed-add-icon",
                tint = MainPurple,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(4.dp).fillMaxWidth()
            )
        }
        Text(text = text, modifier = Modifier.padding(top = 5.dp))
    }
}