package com.example.wall_etmobile.features.home.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.navigation.NavigatorWrapper
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel

@Composable
fun SectionButtons(
    navWrapper: NavigatorWrapper,
    operationsViewModel : OperationsViewModel,){
    ElevatedCard(
        colors =  CardDefaults.elevatedCardColors(MainWhite),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 33.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            SectionButton(
                title = R.string.transfer,
                icon = R.drawable.transfer_icon,
                onClick = {
                    operationsViewModel.clearAll()
                    navWrapper.navigateTransfer()
                },
                )
            Spacer(modifier = Modifier.weight(weight = 1f))
            VerticalDivider(
                modifier = Modifier.height(70.dp),
                color = Color(0xFFE8E8E8)
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            SectionButton(title = R.string.charge_text, icon = R.drawable.charge_icon,
                onClick = {
                    operationsViewModel.clearAll()
                    navWrapper.navigateCharge()
                }
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            VerticalDivider(
                modifier = Modifier.height(70.dp),
                color = Color(0xFFE8E8E8)
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            SectionButton(title = R.string.income_text, icon = R.drawable.enter_icon,
                onClick = {
                    operationsViewModel.clearAll()
                    navWrapper.navigateIncome()
                })
        }
    }

}