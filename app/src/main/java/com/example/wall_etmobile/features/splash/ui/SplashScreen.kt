package com.example.wall_etmobile.features.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var progress by remember { mutableStateOf(0.0f) }
    LaunchedEffect(Unit) {
        while (progress < 1.0f) {
            kotlinx.coroutines.delay(15)
            progress += 0.01f
        }
        val isAuthenticated = authViewModel.isAuthenticated()
        if (isAuthenticated) {
            authViewModel.getCurrentUser()
            navController.navigate(Screen.HOME.route) {
                popUpTo(Screen.SPLASH.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screen.WELCOME.route) {
                popUpTo(Screen.SPLASH.route) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold {
        innerPadding ->
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(230.dp),
                colorFilter = ColorFilter.tint(MainPurple)
            )
            LinearProgressIndicator(
                progress = { progress },
                color = MainPurple,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}