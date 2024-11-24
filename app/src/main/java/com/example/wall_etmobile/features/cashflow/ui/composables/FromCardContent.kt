package com.example.wall_etmobile.features.cashflow.ui.composables
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.ui.screens.calculateTopPadding
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun FromCardContent(
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    onMethodChange : (() -> Unit) -> Unit = {},
    viewModel : CardViewModel = ( LocalContext.current.applicationContext as MyApplication).cardsViewmodel,
    operationsViewModel: OperationsViewModel,
    userViewModel : AuthViewModel,
    transactionViewModel : TransactionViewModel,
){
    val context = LocalContext.current
    val uiState = viewModel.uiCardState

    val totalSteps = 2
    var currentStep by remember { mutableIntStateOf( 0) }

    var selectedObject = remember { mutableStateOf<CreditCard?>(null) }
    var amount = remember { mutableStateOf("0") }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

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
    BackHandler(enabled = currentStep > 0) {
        onclick()
    }
    LaunchedEffect(operationsViewModel.uiState.payment) {
        if (operationsViewModel.uiState.payment == null) return@LaunchedEffect
        operationsViewModel.uiState.payment?.let { transactionViewModel.addPayment(it) }
        currentStep = 0
        navigateToScreen("transaction-details", emptyMap())
    }

    val pages = listOf(

        EnterAmount(
            operationsViewModel = operationsViewModel,
            onClick =  {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        onMethodChange(onclick)
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            }
        ),
        EnterPayment(
            amount = operationsViewModel.uiState.currentAmount ?: "0",
            operationsViewModel = operationsViewModel,
            onClick = {
                operationsViewModel.makePayment(context)
                currentStep = 0;
                onMethodChange(onclick)
            }
        )
    )

        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = (calculateTopPadding() * 0.55).dp)
        ) {
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                HorizontalPager(
                    modifier = Modifier.padding(top = (calculateTopPadding()*0.7).dp),
                    state = pagerState,
                    userScrollEnabled = false
                ) { pageIndex -> pages[pageIndex].invoke()
                }
            }

    }

}