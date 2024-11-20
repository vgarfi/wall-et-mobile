package com.example.wall_etmobile.screens.cashflow

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.design_kit.shared.ActionButton
import com.example.wall_etmobile.ui.theme.MainPurple

@Composable
fun StepIndicator(
    totalSteps: Int,
    modifier: Modifier = Modifier,
    currentStepScale: Float = 2f,
    currentStepColor: Color = MainPurple,
    currentStep : Int = 0,


    ) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var adaptiveHeight by remember { mutableDoubleStateOf(0.0) }
    var adaptiveWidth by remember { mutableDoubleStateOf(0.0) }


    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Center
    ) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> adaptiveWidth = constraints.maxWidth * 0.15
            WindowWidthSizeClass.MEDIUM -> adaptiveWidth = constraints.maxWidth * 0.13
            WindowWidthSizeClass.EXPANDED -> adaptiveWidth = constraints.maxWidth * 0.2
        }
        when (windowSizeClass.windowHeightSizeClass) {
            WindowHeightSizeClass.COMPACT -> adaptiveHeight = constraints.maxHeight * 0.02
            WindowHeightSizeClass.MEDIUM -> adaptiveHeight = constraints.maxHeight * 0.004
            WindowHeightSizeClass.EXPANDED -> adaptiveHeight = constraints.maxHeight * 0.002
        }

        val stepWidth = adaptiveWidth / (totalSteps + (currentStepScale - 1) + (totalSteps - 1) * 0.5f)
        val stepHeight = adaptiveHeight // Use calculated adaptiveHeight directly
        val spacing = stepWidth * 0.2f

        Row(
            modifier = Modifier.width(adaptiveWidth.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing.dp)
        ) {
            for (step in 0 until totalSteps) {
                Box(
                    modifier = Modifier
                        .width((if (step == currentStep) (stepWidth * currentStepScale) else stepWidth).dp)
                        .height(stepHeight.dp)
                        .background(
                            color = if (step == currentStep) currentStepColor else if (step < currentStep) currentStepColor.copy(alpha = 0.8f) else Color.Gray,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
            }
        }
    }
}



@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview(
    name = "Small Screen",
    device = "spec:width=320dp,height=640dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen landscape",
    device = "spec:height=320dp,width=640dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen",
    device = "spec:width=1500dp,height=800dp,dpi=320", // Custom small screen
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen landscape",
    device = "spec:height=1500dp,width=800dp,dpi=320", // Custom small screen
    showBackground = true,
    showSystemUi = true
)
//@Preview(
//    name = "Large Screen",
//    device = "spec:width=720dp,height=1280dp,dpi=320", // Custom large screen
//    showBackground = true,
//    showSystemUi = true
//)
@Composable
fun StepIndicatorPreview() {
    var currentStep by remember { mutableIntStateOf(0) } // Manage current step in the parent

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 100.dp),
        contentAlignment = Center
    ) {
        // Step Indicator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StepIndicator(
                totalSteps = 3,
                currentStep = currentStep,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    title = "Prev",
                    onClick = { if (currentStep > 0) currentStep-- },
                    width = 150,

                    )
                ActionButton(
                    title = "Next",
                    onClick = { if (currentStep < 2) currentStep++ },
                    width = 150,
                    elevation = true
                )
            }
        }
    }
}
