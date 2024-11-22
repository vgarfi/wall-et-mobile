package com.example.wall_etmobile.screens.auth

import CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.navigation.Screen
import com.example.wall_etmobile.ui.theme.MainWhite

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    return Box(
        modifier = Modifier.fillMaxSize().background(color = MainWhite)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
               "¿Olvidaste tu contraseña?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    append("Te enviaremos un ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("código de recuperación")
                    }
                    append(" a tu correo electrónico para que puedas recuperar tu ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("cuenta")
                    }
                },
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(
                label = "Correo electrónico",
                hint = "juan@gmail.com",
                controller = email,
            )
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate(Screen.VERIFYACCOUNT.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Enviar código de recuperación",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}