package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainGrey

@Composable
fun BottomAppBarButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean = false,
    isTiny : Boolean,
    onClick: () -> Unit
) {
    val iconSize = if (isTiny) 25.dp else 30.dp
    val boxSize = if (isTiny) 0.dp else 2.dp
    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp).padding(horizontal = 2.dp)
    ){
        Icon(
            icon,
            contentDescription = label,
            modifier = Modifier
                .size(iconSize)
                .clickable { onClick() },
            tint = if (isSelected) Color(0xFF6200EA) else MainGrey
        )
        Box (modifier = Modifier.size(boxSize))
        Text(
            text = label,
            color = if (isSelected) Color(0xFF6200EA) else MainGrey,
            fontSize = 10.5.sp
            )
    }
}