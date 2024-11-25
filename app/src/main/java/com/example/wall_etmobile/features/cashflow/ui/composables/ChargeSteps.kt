package com.example.wall_etmobile.features.cashflow.ui.composables

import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import kotlinx.coroutines.launch

@Composable
fun ChargeAmount(
    operationsViewModel : OperationsViewModel,
    onClick: () -> Unit = {},
    ) : @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 80.dp).fillMaxSize()
        ) {
            var amountInput = remember { mutableStateOf(operationsViewModel.uiState.currentAmount ?: "") }
            var messageInput = remember { mutableStateOf(operationsViewModel.uiState.currentMessage ?: "") }

            LaunchedEffect(amountInput.value) {
                operationsViewModel.setAmount(amountInput.value)
            }
            LaunchedEffect(messageInput.value) {
                operationsViewModel.setDescription(messageInput.value)
            }
            Text(text = stringResource(R.string.i_want_to_charge), fontSize = 22.sp)
            Box(modifier = Modifier.height(10.dp))
            AmountInputField(
                onValueChange =  { newAmount ->
                    amountInput.value = newAmount
                },
                textIn = amountInput.value
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Column (
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row (
                ){
                    Text(text = stringResource(R.string.message), fontWeight = FontWeight.Bold)
                    Text(text = " " + stringResource(R.string.optional))
                }
                CustomTextField(
                    hint = stringResource(R.string.write_a_message),
                    label = "",
                    isPassword = false,
                    controller = messageInput,
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            ActionButton(
                title = stringResource(R.string.continue_text) ,
                onClick = onClick,
                elevation = true,
                enabled = amountInput.value.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
fun ChargeQR (
    amount: String,
    message: String,
    onClick: () -> Unit = {},
) : @Composable () -> Unit  {
    return {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 15.dp).fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(0.12f))
                Text(text = stringResource(R.string.you_are_charging), fontSize = 22.sp)
                Box(modifier = Modifier.height(15.dp))
                Text(text = "$"+amount, style = TextStyle(
                    fontSize = 50.sp,
                    color = MainPurple,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),)
                Spacer(modifier = Modifier.weight(0.25f))
                    Box (modifier = Modifier.height(300.dp)){
                        Image(
                            painter = painterResource(id = R.drawable.qr),
                            contentDescription = "qr",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Box(modifier = Modifier.height(25.dp))
                    Row (
                    ){
                        Text(text = stringResource(R.string.message), fontWeight = FontWeight.Bold)
                        Text(text = if (message.isNotEmpty()) ": $message" else ": " + stringResource(R.string.no_message))
                    }
                Spacer(modifier = Modifier.weight(0.4f))
                ActionButton(
                    title = stringResource(R.string.back_to_home) ,
                    onClick = onClick,
                    elevation = true,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }



    }

}