package com.example.wall_etmobile.core.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.wall_etmobile.core.designKit.TransactionTypeStyle

data class MovementData(
    val id: Int,
    val tileHeight: Dp,
    val tileWidth: Dp? = null,
    val title: String,
    val titleSize: TextUnit,
    val subTitle: String,
    val subTitleSize: TextUnit,
    val mount: Double,
    val mountSize: TextUnit,
    val transactionTypeStyle: TransactionTypeStyle,
    val onClick: (Int) -> Unit,
    val clickable: Boolean = false
)