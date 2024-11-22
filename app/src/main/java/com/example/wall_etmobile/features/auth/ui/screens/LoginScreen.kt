package com.example.wall_etmobile.features.auth.ui.screens

import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.features.auth.ui.composables.RememberLoginCheckbox
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            CustomTextField(
                label = "Correo electrónico",
                hint = "juan@gmail.com",
                controller = email,
            )
            Spacer(modifier = Modifier.size(16.dp))
            CustomTextField(
                label = "Contraseña",
                hint = "********",
                controller = password,
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RememberLoginCheckbox()
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate(Screen.FORGOTPASSWORD.route)
                        }
                ) {
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        fontSize = 14.sp,
                        color = MainPurple,
                    )
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate(Screen.HOME.route)
                },
                modifier = Modifier.fillMaxWidth())
            {
                Text(
                    text = "Inicia sesión",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            TextButton(
                onClick = {
                    navController.navigate(Screen.REGISTER.route)
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "¿No tenés cuenta?",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Registrate",
                    fontSize = 16.sp,
                    color = MainPurple
                )
            }
        }
    }
}
