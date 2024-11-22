package com.example.wall_etmobile.ui.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class FavoriteTileData(
    val tileHeight: Dp,
    val tileWidth: Dp? = null,
    val title: String,
    val titleSize: TextUnit,
    val bankAccounts: List<@Composable () -> Unit>,
    val userImage: @Composable () -> Unit,
    val isFavorite: Boolean,
    val onClick: () -> Unit
)
