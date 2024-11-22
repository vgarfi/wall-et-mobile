package com.example.wall_etmobile.features.transactions.ui.designKit

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.wall_etmobile.R

@Composable
fun FilterButton(
    width: Dp,
    onClick: () -> Unit
){
    ElevatedButton(
        modifier = Modifier
            .width(width)
            .fillMaxHeight(),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.background)
    ) {
        Icon(
            contentDescription = "filter-icon",
            imageVector = Icons.Filled.Menu,
            tint = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.filter),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}