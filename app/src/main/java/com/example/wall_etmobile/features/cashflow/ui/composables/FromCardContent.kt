package com.example.wall_etmobile.features.cashflow.ui.composables
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.design_kit.shared.ActionButton
import com.example.wall_etmobile.design_kit.shared.EnterAmount
import com.example.wall_etmobile.design_kit.shared.EnterPayment
import kotlinx.coroutines.launch

@Composable
fun FromCardContent(){

    val totalSteps = 2
    var currentStep by remember { mutableIntStateOf( 0) }

    var selectedObject = remember { mutableStateOf<CreditCardInfo?>(null) }
    var amount = remember { mutableStateOf("0") }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val pages = listOf(
        EnterAmount(
            onAmountChange = amount
        ),
        EnterPayment(
            cardsInfo = getSampleCards(),
            selectedObject = { selectedObject.value = it },
            amount = amount.value,
        )
    )
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = calculateTopPadding().dp)
        ) {
            LazyColumn (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
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
                        modifier = Modifier.fillMaxWidth(),
                        elevation = true,
                        title = if (currentStep == 0) "Continuar" else "Transferir",
                        onClick = {
                            if (currentStep == 0) {
                                coroutineScope.launch {
                                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                                        currentStep++
                                        pagerState.animateScrollToPage(currentStep)
                                    }
                                }
                            } else {
                                currentStep = 0;
//                        navigateToScreen("transaction-details", emptyMap())
                            }
                        })
                }
            }

    }

}