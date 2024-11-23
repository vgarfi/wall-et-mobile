package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.example.wall_etmobile.R
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowStepIndicator
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferAmount
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferPayment
import com.example.wall_etmobile.features.cashflow.ui.composables.TransferTo
import kotlinx.coroutines.launch

@Composable
fun TransferToScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    target: String?,
    page: Int?,
    contactName: String?,
    contactDetail: String?
) {
    var userId = remember { mutableStateOf("") }
    var message = remember { mutableStateOf("") }
    var selectedObject = remember { mutableStateOf<CreditCard?>(null) }
    var amount = remember { mutableStateOf("0") }

    val totalSteps = 3
    var currentStep by remember { mutableIntStateOf(page ?: 0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val fixedStrings = when (target) {
        "user" -> listOf(stringResource(R.string.email), stringResource(R.string.example_mail))
        "account" -> listOf("CBU/CVU", stringResource(R.string.enter_cvu))
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
    val pages = listOf(
        TransferTo(
            topPadding = calculateTopPadding(),
            hint = fixedStrings[1],
            header = fixedStrings[0],
            onValueChange = userId,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            }
        ),
        TransferAmount(
            topPadding = calculateTopPadding(),
            contactDetails = userId.value,
            contactName = contactName ?: "Desconocido",
            onValueChange = message,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            },
            onAmountChange = amount
        ),
        TransferPayment(
            topPadding = calculateTopPadding(),
            contactDetails = userId.value,
            contactName = contactName ?: "Desconocido",
            onValueChange = remember { mutableStateOf("") },
            message = message.value,
            cardsInfo = getSampleCards(),
            selectedObject = { selectedObject.value = it },
            amount = amount.value,
            buttonText = stringResource(R.string.transfer),
            buttonEnabled = true,
            onClick = {
                currentStep = 0
                navigateToScreen("transaction-details", emptyMap())
            }
        )
    )

    CashFlowBaseScaffold(bigText = stringResource(R.string.transfer), navController = navController, onArrowClick = onclick) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = calculateTopPadding().dp)
        ) {
            HorizontalPager(
                modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp),
                state = pagerState,
                userScrollEnabled = false
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
