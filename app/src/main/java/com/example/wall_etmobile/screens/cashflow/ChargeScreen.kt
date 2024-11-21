package com.example.wall_etmobile.screens.cashflow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.example.wall_etmobile.design_kit.shared.ActionButton
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import kotlinx.coroutines.launch

@Composable
fun ChargeScreen(
    navController: NavController
) {
    var amount by remember { mutableStateOf("") }
    var message = remember { mutableStateOf("") }

    val totalSteps = 2
    var currentStep by remember { mutableIntStateOf( 0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val pages = listOf(
        ChargeAmount(
            onValueChange =  { newAmount ->
                amount = newAmount
            },
            messageController = message
        ),
        ChargeQR(amount = amount, message = message.value)
    )
    CashFlowBaseScaffold(bigText = "Cobrar", navController = navController) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = calculateTopPadding().dp)
        ) {
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ){
                HorizontalPager(
                    modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp),
                    state = pagerState,
                    userScrollEnabled = false
                ) { pageIndex -> pages[pageIndex].invoke()
                }
                ActionButton(
                    title = if (currentStep == 0) "Continuar" else "Compartir link de pago" ,
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < pagerState.pageCount - 1) {
                                currentStep++
                                pagerState.animateScrollToPage(currentStep)
                            }
                        }
                    },
                    elevation = true,
                    enabled = amount.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }

}

@Composable
fun calculateTopPadding(): Int {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 40
        WindowHeightSizeClass.MEDIUM -> 60
        WindowHeightSizeClass.EXPANDED -> 100
        else -> 1
    }
}