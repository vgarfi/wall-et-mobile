package com.example.wall_etmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.theme.MainWhite
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.example.wall_etmobile.design_kit.home.CvuDialog
import com.example.wall_etmobile.design_kit.home.HomeHeader
import com.example.wall_etmobile.design_kit.home.MountVisor
import com.example.wall_etmobile.design_kit.home.SectionButtons

@Composable
fun HomeScreen(navController: NavController) {
    var showCvu by rememberSaveable { mutableStateOf(false) }
    var showMoney by rememberSaveable { mutableStateOf(true) }
    val clipboardManager = LocalClipboardManager.current
    val cvu = "12321312312331231"
    val username = "Valentin"

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MainWhite)) {
        Image(
            painter = painterResource(id = R.drawable.home_header),
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
                .padding(30.dp)
        ) {
            HomeHeader(onClick = { showCvu = !showCvu })
            MountVisor(
                mount = 12672.68,
                onClick = { showMoney = !showMoney },
                showMoney = showMoney
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            SectionButtons(navController)
        }
    }
    if (showCvu) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            CvuDialog(
                onDismissRequest = { showCvu = false },
                onCopyCvu = { clipboardManager.setText(AnnotatedString(cvu)) },
                cvu = cvu,
                username = username
            )
        }
    }
}


