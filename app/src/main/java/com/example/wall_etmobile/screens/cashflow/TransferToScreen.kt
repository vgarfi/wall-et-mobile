package com.example.wall_etmobile.screens.cashflow

import CustomTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.design_kit.shared.ActionButton
import kotlinx.coroutines.launch

@Composable
fun TransferToScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    target : String?,
    page : Int?,
    to : String?
){
    val totalSteps by remember { mutableIntStateOf(3) }
    var currentStep by remember { mutableIntStateOf(page?.toInt() ?: 0) }
    var userId by remember { mutableStateOf("") }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var topPadding by remember { mutableIntStateOf(0) }
    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{topPadding= 40}
        WindowHeightSizeClass.MEDIUM ->{topPadding= 60}
        WindowHeightSizeClass.EXPANDED ->{topPadding= 160 }
        else -> {0.dp}
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {2} , initialPage = page ?:0 )
    var pages  = listOf(
        TransferTo(topPadding, onValueChange = remember { mutableStateOf(userId) }, onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                   if (currentStep < totalSteps) {
                    currentStep++
                   }
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }

            }
        }
        ),
        TransferTo(topPadding, onValueChange = remember { mutableStateOf(userId) }, onClick = { }
        ))

    CashFlowBaseScaffold(bigText = "Transferir", navController = navController) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = topPadding.dp)
        ) {

            HorizontalPager(
                modifier = Modifier.padding(top = (topPadding*0.1).dp),
                state = pagerState,

            ) { page ->
                pages[page].invoke()
            }

        }
            /*
            var email by remember { mutableStateOf("") }
            Column(modifier = Modifier.padding(top = topPadding.dp)) {
                CustomTextField(
                    hint = "",
                    label = "Email",
                    isPassword = false,
                    controller = remember { mutableStateOf(email) },
                )
                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom

                    ) {
                    ActionButton(title = "Continuar", onClick = {}, elevation = true)
                }
            }

             */

    }

}
@Preview(showBackground = true)
@Composable
fun PreviewTransferToScreen() {
    // Sample data for preview purposes
    val navController = NavController(LocalContext.current)

    // Sample target to display
    val mockTarget = "bordatomas1@gmail.com"

    TransferToScreen(
        navController = navController,
        target = mockTarget,
        page = 1,
        to = "user"
    )
}