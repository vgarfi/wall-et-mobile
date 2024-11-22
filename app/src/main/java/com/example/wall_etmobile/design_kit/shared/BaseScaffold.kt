package com.example.wall_etmobile.design_kit.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun BaseScaffold (
    tinyText: String,
    bigText: String,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MainWhite)) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TitleRow(tinyText = tinyText, bigText = bigText)
            Box(modifier = Modifier.height(55.dp))
            content()
        }
    }
}

@Preview
@Composable
fun BaseScaffoldPreview(){
    BaseScaffold(tinyText = "tus", bigText = "Tarjetas" ) {
        
    }
}