package com.example.wall_etmobile.ui.data

import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.wall_etmobile.core.designKit.TransactionTypeStyle

data class MovementData(
    val tileHeight: Dp,
    val tileWidth: Dp? = null,
    val title: String,
    val titleSize: TextUnit,
    val subTitle: String,
    val subTitleSize: TextUnit,
    val mount: Double,
    val mountSize: TextUnit,
    val transactionTypeStyle: TransactionTypeStyle,
    val onClick: () -> Unit
)