package com.example.wall_etmobile.design_kit.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainPurple
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Copy

@Composable
fun CopyCVU(
    number: String,
) {
    val clipboardManager = LocalClipboardManager.current
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFE6E6E6),
                    shape = RoundedCornerShape(10.dp)
                )
                .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = number,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))

            IconButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(number))
                },
                modifier = Modifier.size(22.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Copy,
                    contentDescription = "Copiar número",
                    tint = MainPurple
                )
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 7.dp)
        ){
            Icon(imageVector = Icons.Default.Info, contentDescription = "info",  tint = MainGrey, modifier = Modifier.size(14.dp))
            Box(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(R.string.cvu_info),
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                color = MaterialTheme.colorScheme.outline,
            )
        }

    }
}
