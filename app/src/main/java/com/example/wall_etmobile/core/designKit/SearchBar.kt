package com.example.wall_etmobile.core.designKit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R

@Composable
fun SearchBar(
    width: Dp,
    searchText: MutableState<String>
) {
    Box(
        modifier = Modifier
            .width(width = width)
            .padding(horizontal = 8.dp)
            .background(Color.Transparent)
    ) {
        CustomTextField(
            label = stringResource(R.string.search_label),
            hint = "",
            isPassword = false,
            controller = searchText,
            prefix = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search-icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}