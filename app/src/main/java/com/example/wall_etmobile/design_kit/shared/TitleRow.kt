package com.example.wall_etmobile.design_kit.shared

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.VectorSquare
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.fontawesomeicons.*
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.ui.theme.MainGrey
import com.example.wall_etmobile.ui.theme.MainWhite
import compose.icons.fontawesomeicons.solid.Question
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
            .padding(12.dp)
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