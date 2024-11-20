package com.example.wall_etmobile.screens.auth

import CustomTextField
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.screens.auth.composables.RememberLoginCheckbox
import com.example.wall_etmobile.screens.auth.composables.TermsCheckbox
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite
import com.pathak.barberapp.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController) {
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
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = "Correo electrónico",
                hint = "juan@gmail.com",
                controller = email,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = "Nombre completo",
                hint = "Juan Pérez",
                controller = email,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = "Contraseña",
                hint = "Al menos 8 caracteres",
                controller = password,
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = "Confirme su contraseña",
                hint = "Confirme su contraseña",
                controller = password,
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(24.dp))
            ElevatedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth())
            {
                Text(
                    text = "Registrarse",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            TermsCheckbox()
            Spacer(modifier = Modifier.weight(1f))
            // TODO: cambiar boton al correcto
            TextButton(
                onClick = {
                    navController.navigate(Screen.Login.route)
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "¿Ya tenés una cuenta?",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Iniciá sesión",
                    fontSize = 16.sp,
                    color = MainPurple
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
