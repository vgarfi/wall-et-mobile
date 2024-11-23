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
fun WithCVUContent() {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().padding(top = 86.dp)
    ) {
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
        Text(text = stringResource(R.string.using_svu))
        Box(modifier = Modifier.height(20.dp))
        CopyCVU(number = "0042078575744892407")
        Spacer(modifier = Modifier.weight(1f))
        ActionButton(
            title = stringResource(R.string.share_cvu),
            onClick = {},
            elevation = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}