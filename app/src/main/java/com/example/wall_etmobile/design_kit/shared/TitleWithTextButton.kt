package com.example.wall_etmobile.design_kit.shared

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TitleWithTextButton(
    @StringRes title: Int,
    onClick: () -> Unit,
    @StringRes textButton: Int
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(title),
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
        ElevatedButton(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = stringResource(textButton),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}