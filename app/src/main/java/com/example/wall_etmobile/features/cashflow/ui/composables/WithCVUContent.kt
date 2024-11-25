package com.example.wall_etmobile.features.cashflow.ui.composables
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.designKit.CopyCVU
@Composable
fun WithCVUContent(
    onClick : ()->Unit= {}
) {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().padding(top = 86.dp).verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(0.15f))
        Text(text = stringResource(R.string.user_your_cvu), fontSize = 22.sp)
        Box(modifier = Modifier.height(20.dp))
        Box (modifier = Modifier
            .height(300.dp)
            .padding(vertical = 30.dp)){
            Image(
                painter = painterResource(id = R.drawable.cvu),
                contentDescription = "CVU",
                modifier = Modifier.fillMaxSize()
                )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Text(text = stringResource(R.string.using_svu))
        Box(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.weight(0.2f))
        CopyCVU(number = "0042078575744892407")
        Spacer(modifier = Modifier.weight(0.2f))
        ActionButton(
            title = stringResource(R.string.back_to_home),
            onClick = onClick,
            elevation = true,

        )
    }
}