package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.wall_etmobile.core.data.MovementData

@Composable
fun MovementTileList(
    movements: List<MovementData>
){
    LazyColumn {
        items(items = movements) {
            MovementTile(
                id = it.id,
                tileHeight = it.tileHeight,
                title = it.title,
                titleSize = it.titleSize,
                subTitle = it.subTitle,
                subTitleSize = it.subTitleSize,
                mount = it.mount,
                mountSize = it.mountSize,
                transactionTypeStyle = it.transactionTypeStyle,
                onClick = it.onClick,
                clickable = it.clickable
            )
        }
    }
}