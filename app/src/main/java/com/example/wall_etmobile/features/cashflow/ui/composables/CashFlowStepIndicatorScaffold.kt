package com.example.wall_etmobile.features.cashflow.ui.composables


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
fun CashFlowStepIndicator (
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ){
        StepIndicator(currentStep = currentStep, totalSteps = totalSteps)
        content()
    }
}