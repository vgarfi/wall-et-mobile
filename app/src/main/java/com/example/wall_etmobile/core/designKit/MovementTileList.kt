package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.data.MovementData
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun MovementTileList(
    movements: List<MovementData>
){
    LazyColumn {
        if (movements.isEmpty()) {
            item {
                CustomCard (
                    isDashed = true,
                    modifier = Modifier.fillMaxHeight().padding(vertical = 20.dp),
                    backgroundColor = MainWhite
                ){
                    Text(
                        stringResource(R.string.no_transactions),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                        color = MainGrey.copy(alpha = 0.7f),
                        modifier = Modifier.fillMaxSize().padding(vertical = 30.dp)
                    )
                }
            }
        } else {
            items(items = movements) {
                MovementTile(
                    id = it.id,
                    tileHeight = it.tileHeight,
                    title = it.title,
                    titleSize = it.titleSize,
                    subTitle = it.subTitle,
                    subTitleSize = it.subTitleSize,
                    mount = it.mount,
                    mountSize = it.mountSize,
                    transactionTypeStyle = it.transactionTypeStyle,
                    onClick = it.onClick,
                    clickable = it.clickable
                )
            }
        }

    }
}