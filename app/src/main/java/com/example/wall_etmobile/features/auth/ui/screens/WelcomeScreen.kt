package com.example.wall_etmobile.features.auth.ui.screens

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.GrayText
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun WelcomeScreen(navController: NavController) {
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "HCI Wallet Logo",
                colorFilter = ColorFilter.tint(MainPurple),
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Wall-et",
                fontSize = 40.sp,
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = stringResource(R.string.your_new_ally_in),
                fontSize = 14.sp,
                color = GrayText
            )
            Text(
                text = stringResource(R.string.personal_finance),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GrayText
            )
            Spacer(modifier = Modifier.weight(1f))
            ActionButton(
                onClick = {
                    navController.navigate(Screen.LOGIN.route)
                }, modifier = Modifier
                    .fillMaxWidth().height(48.dp)
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.sign_in),
                elevation = true,
            )
            ActionButton(
                onClick = {
                    navController.navigate(Screen.REGISTER.route)
                }, modifier = Modifier
                    .fillMaxWidth().height(48.dp)
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.create_account),
                elevation = true,
            )
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}