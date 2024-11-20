package com.example.wall_etmobile.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.theme.LightBackground
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite
import com.pathak.barberapp.navigation.Screen

@Composable
fun VerifyAccountScreen(navController: NavController) {
    val otpValue = remember { mutableStateListOf<String>("", "", "", "") }
    val focusRequesters = remember { List(4) { FocusRequester() } }
    val localFocusManager = LocalFocusManager.current

    fun updateOtpValue(index: Int, value: String) {
        if (index in otpValue.indices && value.length <= 1) {
            otpValue[index] = value

            if (value.isNotEmpty() && index < 3) {
                focusRequesters[index + 1].requestFocus()
            } else if (value.isNotEmpty() && index == 3) {
                localFocusManager.clearFocus()
            } else if (value.isEmpty() && index > 0) {
                focusRequesters[index - 1].requestFocus()
            }
        }
    }

    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.verify_account),
                        contentDescription = "Verify Account",
                        modifier = Modifier
                            .size(350.dp)
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = "Verifica tu cuenta",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Por favor ingresa el código enviado a"
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "valentin.garfi@gmail.com",
                    color = MainPurple,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 0 until 4) {
                        OtpTextField(
                            value = otpValue.getOrNull(i)?.toString() ?: "",
                            onValueChange = { updateOtpValue(i, it) },
                            focusRequester = focusRequesters[i],
                        )
                    }
                }
                Text(
                    text = "Reenviar código",
                    color = MainPurple,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                )
                Spacer(modifier = Modifier.height(32.dp))
                ElevatedButton(
                    onClick = {
                        navController.navigate(Screen.RestorePassword.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(text = "Confirmar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField(value: String, onValueChange: (String) -> Unit, focusRequester: FocusRequester) {
    var isFocused by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (isFocused) MainPurple else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .focusRequester(focusRequester),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = LightBackground,
            unfocusedContainerColor = LightBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
