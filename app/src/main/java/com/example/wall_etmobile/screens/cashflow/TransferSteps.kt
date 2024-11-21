package com.example.wall_etmobile.screens.cashflow

import CustomTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.ActionButton



fun TransferTo(
    topPadding: Int,
    onClick : () -> Unit ={},
    onValueChange :MutableState<String>
): @Composable () -> Unit {
    return {
        Column(modifier = Modifier.padding(top = topPadding.dp)) {
            CustomTextField(
                hint = "",
                label = "Email",
                isPassword = false,
                controller = onValueChange,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ActionButton(
                    title = "Continuar",
                    onClick = onClick,
                    elevation = true
                )
            }
        }
    }
}

fun TransferAmount(
    topPadding: Int,
    onClick : () -> Unit ={},
    onValueChange :MutableState<String>,
    contactName : String = "",
    contactDetails : String = ""

): @Composable () -> Unit {
    return {
        Column(modifier = Modifier.padding(top = topPadding.dp)) {
            ContactTransferTile(
                icon = R.drawable.logo,
                contactName = contactName,
                contactDetails = contactDetails,
            )

            CustomTextField(
                hint = "",
                label = "Email",
                isPassword = false,
                controller = onValueChange,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ActionButton(
                    title = "Continuar",
                    onClick = onClick,
                    elevation = true
                )
            }
        }
    }
}
