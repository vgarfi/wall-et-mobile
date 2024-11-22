package com.example.wall_etmobile.features.auth.ui.screens

import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun RestorePasswordScreen(navController: NavController) {
    val newPassword = remember { mutableStateOf("") }
    val newPasswordConfirmation = remember { mutableStateOf("") }
    Box (
        modifier = Modifier.fillMaxSize().background(color = MainWhite)
    ) {
        Column (
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Restablecé tu contraseña",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Asegurate de que tu nueva contraseña sea segura y única.",
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                label = "Nueva contraseña",
                hint = "Ingresa tu nueva contraseña",
                controller = newPassword,
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                label = "Confirma tu nueva contraseña",
                hint = "Ingresa tu nueva contraseña",
                controller = newPasswordConfirmation,
            )
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Restablecer contraseña",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}