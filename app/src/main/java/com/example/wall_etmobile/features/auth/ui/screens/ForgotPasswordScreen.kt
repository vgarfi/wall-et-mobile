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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
               stringResource(R.string.forgot_password),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.we_will_send_a))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.recovery_code))
                    }
                    append(stringResource(R.string.to_your_email_address_so_you_can_recover_your))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.account))
                    }
                },
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(
                label = stringResource(R.string.email),
                hint = "juan@gmail.com",
                controller = email,
            )
            Spacer(modifier = Modifier.height(30.dp))
            ActionButton(
                onClick = {
                    navController.navigate(Screen.VERIFYACCOUNT.route)
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                title = stringResource(R.string.send_recovery_code),
                elevation = true,
            )
        }
    }
}