package com.example.wall_etmobile.features.home.ui.designKit

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R

@Composable
fun SectionButton (
    @StringRes title: Int,
    icon: Int,
    onClick: () -> Unit
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 17.dp)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.height(3.dp))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "seccion",
        )
        Box(modifier = Modifier.height(12.dp))
        Text(text = stringResource(title), fontWeight = FontWeight.W600, fontSize = 16.sp)
    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun SectionButtonPreview () {
    SectionButton(title = R.string.income_text, icon = R.drawable.enter_icon) {

    }
}