package com.example.wall_etmobile.design_kit.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import androidx.compose.material3.Icon
import com.example.wall_etmobile.core.theme.MainWhite
import compose.icons.fontawesomeicons.solid.QuestionCircle

@Composable
fun TitleRow(
    tinyText: String,
    bigText: String
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Spacer(Modifier.weight(1f))
        SectionTitle(tinyText = tinyText, bigText = bigText)
        Spacer(Modifier.weight(0.8f))
        Icon(
            FontAwesomeIcons.Solid.QuestionCircle,
            contentDescription = "FAQ",
            modifier = Modifier
                .size(18.dp)
                .clickable {},
            tint = MainWhite
        )
    }
}

@Preview
@Composable
fun TitleRowPreview() {
    TitleRow(tinyText = "tus", bigText = "Tarjetas")
}