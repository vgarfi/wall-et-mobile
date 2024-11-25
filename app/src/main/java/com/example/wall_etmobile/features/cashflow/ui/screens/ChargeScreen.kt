package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import kotlinx.coroutines.launch

@Composable
fun ChargeScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    operationsViewModel : OperationsViewModel,
) {
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
    BackHandler(enabled = currentStep > 0) {
        onclick()
    }
    val pages = listOf(
        ChargeAmount(
            operationsViewModel = operationsViewModel,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            }
        ),
        ChargeQR(
            amount = operationsViewModel.uiState.currentAmount ?: "",
            message = operationsViewModel.uiState.currentMessage ?:"",
            onClick = {
                currentStep--
                operationsViewModel.clearAll()
                navigateToScreen("home", emptyMap())
            })
    )


    CashFlowBaseScaffold(bigText = stringResource(R.string.charge), navController = navController, onArrowClick = onclick) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = (calculateTopPadding()*0.5).dp)
        ) {
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())
            ){
                HorizontalPager(
                    modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp),
                    state = pagerState,
                    userScrollEnabled = false
                ) { pageIndex -> pages[pageIndex].invoke()
                }
            }

        }
    }

}