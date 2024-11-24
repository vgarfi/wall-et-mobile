package com.example.wall_etmobile.features.home.ui.designKit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.data.FavoriteTileData
import com.example.wall_etmobile.core.designKit.FavoriteTile
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun FavoriteDialog(
    onDismissRequest: () -> Unit,
    height: Dp,
    favorites: List<FavoriteTileData>
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Column(
                modifier = Modifier
                    .height(height = height)
                    .background(color = MainWhite)
            ) {
                Text(
                    text = stringResource(R.string.favorite),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    color = MainPurple,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn() {
                    items(items = favorites) {
                        FavoriteTile(
                            tileHeight = it.tileHeight,
                            tileWidth = it.tileWidth,
                            title = it.title,
                            titleSize = it.titleSize,
                            bankAccounts = it.bankAccounts,
                            userImage = it.userImage,
                            isFavorite = it.isFavorite,
                            onClick = it.onClick
                        )
                    }
                }
            }
        }
    }
}