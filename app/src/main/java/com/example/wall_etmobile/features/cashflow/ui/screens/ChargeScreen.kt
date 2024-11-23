package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowStepIndicator
import com.example.wall_etmobile.features.cashflow.ui.composables.ChargeAmount
import com.example.wall_etmobile.features.cashflow.ui.composables.ChargeQR
import kotlinx.coroutines.launch

@Composable
fun ChargeScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
) {
    var amount by remember { mutableStateOf("") }
    var message = remember { mutableStateOf("") }

    val totalSteps = 2
    var currentStep by remember { mutableIntStateOf( 0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val onclick : () -> Unit = {
        if (currentStep <= 0){
            navigateToScreen("home", emptyMap())
        }
        else{
            currentStep--
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentStep)
            }
        }
    }
    val pages = listOf(
        ChargeAmount(
            onValueChange =  { newAmount ->
                amount = newAmount
            },
            messageController = message
        ),
        ChargeQR(amount = amount, message = message.value)
    )
    CashFlowBaseScaffold(bigText = stringResource(R.string.charge), navController = navController, onArrowClick = onclick) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = calculateTopPadding().dp)
        ) {
            LazyColumn (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ){
                item {
                HorizontalPager(
                    modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp),
                    state = pagerState,
                    userScrollEnabled = false
                ) { pageIndex -> pages[pageIndex].invoke()
                }
                }
                item {
                ActionButton(
                    title = if (currentStep == 0) stringResource(R.string.continue_text) else stringResource(R.string.back_to_home) ,
                    onClick = {
                        if (currentStep == 0) {
                            coroutineScope.launch {
                                if (pagerState.currentPage < pagerState.pageCount - 1) {
                                    currentStep++
                                    pagerState.animateScrollToPage(currentStep)
                                }
                            }
                        }
                        else{
                            navigateToScreen("home", emptyMap())
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

}