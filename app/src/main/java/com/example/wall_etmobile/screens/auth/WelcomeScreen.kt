package com.example.wall_etmobile.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.theme.GrayText
import com.example.wall_etmobile.ui.theme.MainWhite
import com.pathak.barberapp.navigation.Screen

@Composable
fun WelcomeScreen(navController: NavController) {
    return Box(
        modifier = Modifier.fillMaxSize().background(color = MainWhite)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "HCI Wallet Logo",
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Wall-et",
                fontSize = 40.sp,
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Tu nuevo aliado en las",
                fontSize = 14.sp,
                color = GrayText
            )
            Text(
                text = "finanzas personales",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GrayText
            )
            Spacer(modifier = Modifier.weight(1f))
            // TODO: Reemplazar por botones correctos
            ElevatedButton(onClick = {
                navController.navigate(Screen.Login.route)
            }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            ElevatedButton(onClick = {
                navController.navigate(Screen.Register.route)
            }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Text(
                    text = "Crear cuenta",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}