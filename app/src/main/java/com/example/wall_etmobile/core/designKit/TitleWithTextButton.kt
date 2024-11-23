package com.example.wall_etmobile.core.designKit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainPurple
import compose.icons.FontAwesomeIcons

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
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = MainBlack,
            modifier = Modifier.weight(1f)
        )
        TextButton(
            onClick = {
                onClick()
            },
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(textButton),
                    color = MainPurple
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "view",
                    tint = MainPurple,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}