package com.example.wall_etmobile.features.transactions.ui.designKit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R

@Composable
fun FilterIndicator(
    onClick: () -> Unit,
    formatedStartDate: String,
    formatedEndDate: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$formatedStartDate - $formatedEndDate",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp)
        )
        IconButton(
            onClick = {
                onClick()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.cancel),
                contentDescription = "quit-filter",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}