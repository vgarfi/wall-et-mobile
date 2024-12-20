package com.example.wall_etmobile.features.home.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite
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
            fontSize = 20.sp,
            color = MainWhite
        )
        Box(Modifier.height(10.dp))
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            if (showMoney) {
                Text(
                    text = "$ ${"%.2f".format(mount)}",
                    fontSize = 43.sp,
                    fontWeight = FontWeight.Black,
                    color = MainWhite,
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.Eye,
                        contentDescription = "Eye",
                        tint = MainWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                Text(
                    text = "$ · · · · · · ·",
                    fontSize = 43.sp,
                    fontWeight = FontWeight.Black,
                    color = MainWhite
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.EyeSlash,
                        contentDescription = "EyeSlash",
                        tint = MainWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}