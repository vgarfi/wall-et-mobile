package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupPositionProvider
import androidx.navigation.NavController
import com.example.wall_etmobile.core.theme.MainWhite
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.QuestionCircle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashFlowTitleRow(
    bigText: String,
    navController: NavController,
    arrowAction: (() -> Unit)? = null
) {
    val tooltipState = TooltipState()
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
                    if (arrowAction != null) {
                        arrowAction()
                    } else {
                        navController.popBackStack()
                    }
                },
            tint = MainWhite
        )
        Spacer(modifier = Modifier.weight(0.4f))
        Text(text = bigText, color = MainWhite, fontSize = 22.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.weight(0.4f))

    }
}