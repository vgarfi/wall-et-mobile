package com.example.wall_etmobile.screens.cashflow

import CustomTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun TransferToScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    target : String?

){
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var topPadding by remember { mutableIntStateOf(0) }


    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{topPadding= 40}
        WindowHeightSizeClass.MEDIUM ->{topPadding= 30}
        WindowHeightSizeClass.EXPANDED ->{topPadding= 160 }
        else -> {0.dp}
    }
    CashFlowBaseScaffold(bigText = "Transferir a $target", navController = navController) {
        CashFlowStepIndicator(currentStep = 0, totalSteps = 3, modifier = Modifier.padding(top = topPadding.dp))

        {
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
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewTransferToScreen() {
    // Sample data for preview purposes
    val navController = NavController(LocalContext.current)

    // Sample target to display
    val mockTarget = "Usuario de Wall-et"

    TransferToScreen(
        navController = navController,
        target = mockTarget
    )
}