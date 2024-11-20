package com.example.wall_etmobile.design_kit.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.navigation.NavigatorWrapper
import com.example.wall_etmobile.ui.theme.MainWhite
import com.example.wall_etmobile.navigation.Screen

@Composable
fun SectionButtons(navWrapper: NavigatorWrapper){
    ElevatedCard(
        colors =  CardDefaults.elevatedCardColors(MainWhite),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 25.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            SectionButton(
                title = R.string.transfer_text,
                icon = R.drawable.transfer_icon,
                onClick = {
                    navWrapper.navigateTransfer()
                },

                )
            Spacer(modifier = Modifier.weight(weight = 1f))
            VerticalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color(0xFFE8E8E8)
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            SectionButton(title = R.string.charge_text, icon = R.drawable.charge_icon,
                onClick = {
                    navWrapper.navigateCharge()
                }
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            VerticalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color(0xFFE8E8E8)
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            SectionButton(title = R.string.income_text, icon = R.drawable.enter_icon,
                onClick = {
                    navWrapper.navigateIncome()
                })
        }
    }

}