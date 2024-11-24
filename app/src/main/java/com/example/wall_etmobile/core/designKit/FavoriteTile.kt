package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun FavoriteTile(
    tileHeight: Dp,
    tileWidth: Dp? = null,
    title: String,
    titleSize: TextUnit,
    bankAccounts: List<@Composable () -> Unit>,
    userImage: @Composable () -> Unit,
    isFavorite: Boolean,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable(onClick = { onClick() }),
        colors = CardDefaults.cardColors(MainWhite)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
                .then(
                    if (tileWidth == null) Modifier
                        .fillMaxWidth()
                        .height(tileHeight) else Modifier.size(tileWidth, tileHeight)
                )
        ) {
            userImage()
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                Text(
                    text = title,
                    fontSize = titleSize,
                    fontWeight = FontWeight.Normal,
                    color = MainBlack
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    bankAccounts.forEach {
                        it()
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { onClick() }
                ) {
                    var icon = painterResource(R.drawable.heart)
                    if (isFavorite){
                        icon = painterResource(R.drawable.filled_heart)
                    }
                    Icon(
                        painter = icon,
                        contentDescription = "favorite-icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        tint = MainPurple
                    )
                }

        }
    }
}

@Preview
@Composable
fun FavoriteTilePreview(){
    var isFavorite by remember { mutableStateOf(true) }

    FavoriteTile(
        tileHeight = 80.dp,
        title = "Agustin",
        titleSize = 25.sp,
        bankAccounts = listOf(
            { RoundedImage(size = 20.dp) },
            { RoundedImage(size = 20.dp) }
        ),
        userImage = { RoundedImage(size = 80.dp) },
        isFavorite = isFavorite,
        onClick = {
            isFavorite = !isFavorite
        }
    )
}