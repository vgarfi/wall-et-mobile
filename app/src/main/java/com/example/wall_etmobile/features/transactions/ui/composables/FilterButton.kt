package com.example.wall_etmobile.features.transactions.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun FilterButton(
    onClick: () -> Unit
){
    ElevatedButton(
        modifier = Modifier.fillMaxSize(),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.elevatedButtonColors(MainWhite)
    ) {
        Icon(
            contentDescription = "filter-icon",
            imageVector = Icons.Filled.Menu,
            tint = MainPurple
        )
        Text(
            text = stringResource(R.string.filter),
            color = MainPurple
        )
    }
}