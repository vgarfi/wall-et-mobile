package com.example.wall_etmobile.features.auth.ui.screens

import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.features.auth.ui.composables.TermsCheckbox
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

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
                label = stringResource(R.string.email),
                hint = "juan@gmail.com",
                controller = email,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = stringResource(R.string.full_name),
                hint = "Juan Pérez",
                controller = email,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = stringResource(R.string.password),
                hint = stringResource(R.string.at_least_8_characters),
                controller = password,
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(24.dp))
            CustomTextField(
                label = stringResource(R.string.confirm_your_password),
                hint = stringResource(R.string.confirm_your_password),
                controller = password,
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(24.dp))
            ActionButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                title = stringResource(R.string.create_account),
                elevation = true,
            )
            Spacer(modifier = Modifier.size(16.dp))
            TermsCheckbox()
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    navController.navigate(Screen.LOGIN.route)
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 16.sp,
                    color = MainPurple
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
