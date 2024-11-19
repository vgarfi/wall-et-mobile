package com.example.wall_etmobile.data

import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.wall_etmobile.design_kit.shared.TransactionType

data class MovementData(
    val tileHeight: Dp,
    val tileWidth: Dp? = null,
    @StringRes val title: Int,
    val titleSize: TextUnit,
    val subTitle: String,
    val subTitleSize: TextUnit,
    val mount: Double,
    val mountSize: TextUnit,
    val transactionType: TransactionType
)
