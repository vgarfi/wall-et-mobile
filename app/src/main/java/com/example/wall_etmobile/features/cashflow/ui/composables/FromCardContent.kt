package com.example.wall_etmobile.features.cashflow.ui.composables
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.ui.screens.calculateTopPadding
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun FromCardContent(
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    onMethodChange : (() -> Unit) -> Unit = {},
    viewModel : CardViewModel =( LocalContext.current.applicationContext as MyApplication).cardsViewmodel,
){

    val uiState = viewModel.uiCardState

    val totalSteps = 2
    var currentStep by remember { mutableIntStateOf( 0) }

    var selectedObject = remember { mutableStateOf<CreditCard?>(null) }
    var amount = remember { mutableStateOf("0") }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val pages = listOf(
        EnterAmount(
            onAmountChange = amount
        ),
        EnterPayment(
            cards = uiState.cards,
            selectedObject = { selectedObject.value = it },
            amount = amount.value,
        )
    )
    val onclick : () -> Unit = {
        if (currentStep <= 0){
            navigateToScreen("enter", emptyMap())
        }
        else{
            currentStep--
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentStep)
            }
        }
    }

        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = (calculateTopPadding() * 0.55).dp)
        ) {
            LazyColumn (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ){
                item {
                    HorizontalPager(
                        modifier = Modifier.padding(top = (calculateTopPadding()*0.7).dp),
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
                                        onMethodChange(onclick)
                                        pagerState.animateScrollToPage(currentStep)
                                    }
                                }
                            } else {
                                currentStep = 0;
                                onMethodChange(onclick)
//                        navigateToScreen("transaction-details", emptyMap())
                            }
                        })
                }
            }

    }

}