package com.example.wall_etmobile.features.transactions.ui.designKit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainRed
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun FilterIndicator(
    onClick: () -> Unit,
    formatedStartDate: String,
    formatedEndDate: String,
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(MainWhite),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$formatedStartDate - $formatedEndDate",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                color = MainPurple,
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
                    tint = MainRed,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}