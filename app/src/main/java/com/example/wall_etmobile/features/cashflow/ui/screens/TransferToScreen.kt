package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.example.wall_etmobile.R
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowStepIndicator
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferAmount
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferPayment
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferTo
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.transactions.model.TransactionType
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel
import kotlinx.coroutines.launch

@Composable
fun TransferToScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    target: String?,
    page: Int?,
    contactName: String?,
    contactDetail: String?,
    operationsViewModel : OperationsViewModel,
    userViewModel : AuthViewModel,
    cardViewModel : CardViewModel,
    transactionViewModel : TransactionViewModel
) {

    val context = LocalContext.current
    val totalSteps = 3
    LaunchedEffect(Unit) {
        if (!contactDetail.isNullOrEmpty()){
            operationsViewModel.setReceiverID(contactDetail)
        }
        operationsViewModel.getReceiverID()
    }

    var currentStep by remember { mutableIntStateOf(page ?: 0) }
    operationsViewModel.setOperationType(TransactionType.TRANSFER)
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val fixedStrings = when (target) {
        "user" -> listOf(stringResource(R.string.email), stringResource(R.string.example_mail), stringResource(R.string.wallet_account))
        "account" -> listOf("CBU/CVU", stringResource(R.string.enter_cvu), stringResource(R.string.bank_account))
        else -> listOf("", "")
    }

    val onclick : () -> Unit = {
        if (currentStep <= 0){
            navigateToScreen("transfer", emptyMap())
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
        TransferTo(
            hint = fixedStrings[1],
            header = fixedStrings[0],
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            },
            operationsViewModel = operationsViewModel
        ),
        TransferAmount(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            },
            operationsViewModel = operationsViewModel,
            logo = if(target == "user") R.drawable.logo else R.drawable.bank,
            type = fixedStrings[2]
        ),
        TransferPayment(
            buttonText = stringResource(R.string.transfer),
            PaymentBySelfBalance = true,
            onClick = {
                if(target == "user") {
                    operationsViewModel.makePayment(context)
                }else{
                    currentStep = 0
                    navigateToScreen("transaction-details", emptyMap())
                }
            },
            operationsViewModel = operationsViewModel,
            type = fixedStrings[2]
        )
    )

    LaunchedEffect(operationsViewModel.uiState.payment) {
        if (operationsViewModel.uiState.payment == null) return@LaunchedEffect
        operationsViewModel.uiState.payment?.let { transactionViewModel.addPayment(it) }
        currentStep = 0
        navigateToScreen("transaction-details", emptyMap())
    }

    CashFlowBaseScaffold(bigText = stringResource(R.string.transfer), navController = navController, onArrowClick = onclick) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = (calculateTopPadding()*0.5).dp)
        ) {
            HorizontalPager(
                modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp).padding(horizontal = 16.dp),
                state = pagerState,
                userScrollEnabled = false,
            ) { page ->
                pages[page].invoke()
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
        WindowHeightSizeClass.EXPANDED -> 160
        else -> 1
    }
}

fun getSampleCards(): List<CreditCard> {
    return listOf(
        CreditCard(
            number = "4509909098989898",
            holderName = "Tomas Borda",
            expirationDate = "07/25",
            color = 0,
            cvv = "123"
        ),
        CreditCard(
            number = "4509909098980931",
            holderName = "Lucia Mateu",
            expirationDate = "07/29",
            color = 2,
            cvv = "143"
        ),
    )
}
