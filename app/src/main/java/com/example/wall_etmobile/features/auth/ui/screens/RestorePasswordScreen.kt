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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun RestorePasswordScreen(navController: NavController) {
    val newPassword = remember { mutableStateOf("") }
    val newPasswordConfirmation = remember { mutableStateOf("") }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                stringResource(R.string.restore_your_password),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.make_sure_your_new_password_is_secure_and_unique),
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                label = stringResource(R.string.new_password),
                hint = stringResource(R.string.enter_your_new_password),
                controller = newPassword,
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                label = stringResource(R.string.confirm_your_new_password),
                hint = stringResource(R.string.enter_your_new_password),
                controller = newPasswordConfirmation,
            )
            Spacer(modifier = Modifier.height(30.dp))
            ActionButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                title = stringResource(R.string.restore_password),
                elevation = true,
            )
        }
    }
}