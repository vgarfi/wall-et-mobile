package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.core.theme.MainWhite
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.QuestionCircle

@Composable
fun CashFlowTitleRow(
    bigText: String,
    navController: NavController
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
    ){
        Icon(
            FontAwesomeIcons.Solid.ArrowLeft,
            contentDescription = "volver",
            modifier = Modifier
                .size(18.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = MainWhite
        )
        Text(text = bigText, color = MainWhite, fontSize = 22.sp, fontWeight = FontWeight.W600)
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