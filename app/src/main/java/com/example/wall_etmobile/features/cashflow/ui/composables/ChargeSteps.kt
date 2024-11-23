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
import com.example.wall_etmobile.core.theme.MainPurple

@Composable
fun ChargeAmount(
    onValueChange: (String) -> Unit,
    messageController: MutableState<String>,

    ) : @Composable () -> Unit {
    return {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 80.dp)
        ) {
            var amount by remember { mutableStateOf("") }
            Text(text = "Quiero cobrar", fontSize = 22.sp)
            Box(modifier = Modifier.height(10.dp))
            AmountInputField(
                onValueChange =  { newAmount ->
                    amount = newAmount
                    onValueChange(amount)
                }
            )
            Spacer(modifier = Modifier.height(85.dp))
            Column (
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row (
                ){
                    Text(text = stringResource(R.string.message), fontWeight = FontWeight.Bold)
                    Text(text = " " + stringResource(R.string.optional))
                }
                Box(modifier = Modifier.height(5.dp))
                CustomTextField(
                    hint = stringResource(R.string.write_a_message),
                    label = "",
                    isPassword = false,
                    controller = messageController,
                )
            }
        }
    }
}
@Composable
fun ChargeQR (
    amount: String,
    message: String
) : @Composable () -> Unit  {
    return {
        Column {

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Vas a cobrar", fontSize = 22.sp)
                    Box(modifier = Modifier.height(10.dp))
                    Text(text = "$"+amount, style = TextStyle(
                        fontSize = 40.sp,
                        color = MainPurple,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    ),)
                    Column (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Box(modifier = Modifier.height(20.dp))
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
                            Text(text = "Mensaje: ", fontWeight = FontWeight.Bold)
                            Text(text = if (message.isNotEmpty()) message else "(sin mensaje)")
                        }

                    }
                }
            }


    }

}