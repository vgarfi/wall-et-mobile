package com.example.wall_etmobile.design_kit.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash

@Composable
fun MountVisor(mount: Double, showMoney: Boolean, onClick: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.home_mount_text),
            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row {
            if (showMoney) {
                Text(
                    text = "$ ${"%.2f".format(mount)}",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.Eye,
                        contentDescription = "Eye",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                Text(
                    text = "$ ·······",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.EyeSlash,
                        contentDescription = "EyeSlash",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}