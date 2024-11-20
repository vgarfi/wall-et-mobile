package com.example.wall_etmobile.design_kit.shared

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.R

@Composable
fun ActionButton(
    title: String,
    onClick: () -> Unit = {},
    height: Int = 20,
    width: Int = 300,
    modifier: Modifier = Modifier,
    @ColorRes contentColorRes: Int = R.color.white,
    @ColorRes containerColorRes: Int = R.color.purple_500,
    elevation: Boolean = false,
    enabled: Boolean = true,
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val containerColor = colorResource(id = containerColorRes)
    val contentColor = colorResource(id = contentColorRes)
    var adaptativeHeight by remember { mutableDoubleStateOf(0.0)}
    var adaptativeWidth by remember { mutableDoubleStateOf(0.0)}
    val buttonColors: ButtonColors = if (elevation) {
        ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    } else {
        ButtonDefaults.outlinedButtonColors(
            containerColor = contentColor,
            contentColor = containerColor,
        )
    }
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(title),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    )
    val minWidth = textLayoutResult.size.width
    val minHeight = textLayoutResult.size.height

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            adaptativeWidth = (width*1f).toDouble()
        }
        WindowWidthSizeClass.MEDIUM -> {
            adaptativeWidth = (width*1.5f).toDouble()
        }
        WindowWidthSizeClass.EXPANDED -> {
            adaptativeWidth = (width*2f).toDouble()
        }
    }

    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> {
            adaptativeHeight = (height*1f).toDouble()
        }
        WindowHeightSizeClass.MEDIUM -> {
            adaptativeHeight = (height*1.3f).toDouble()
        }
        WindowHeightSizeClass.EXPANDED -> {
            adaptativeHeight = (height*2f).toDouble()
        }
    }


    val adjustedFontSize = if (adaptativeWidth > 300) 18.sp else if (adaptativeWidth > 150) 16.sp else 14.sp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 17.dp)
    ) {
        Box(modifier = Modifier.height(3.dp))
        if (elevation) {
            ElevatedButton(
                onClick = onClick,
                modifier = modifier
                    .widthIn(min = minWidth.dp)
                    .heightIn(min = minHeight.dp)
                    .height(adaptativeHeight.dp)
                    .width(adaptativeWidth.dp)
                    ,
                colors = buttonColors,
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                enabled = enabled
                ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = adjustedFontSize,
                    maxLines = 1,
                    softWrap = true
                )
            }
        }
        else{
            OutlinedButton(
                onClick = onClick,
                modifier = modifier
                    .widthIn(min = minWidth.dp)
                    .heightIn(min = minHeight.dp)
                    .height(adaptativeHeight.dp)
                    .width(adaptativeWidth.dp),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                colors = buttonColors,
                enabled = enabled
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = adjustedFontSize,
                    maxLines = 1,
                    softWrap = true
                )
            }
        }

    }
}


@Preview(
    name = "Small Screen",
    device = "spec:width=320dp,height=640dp,dpi=320", // Custom small screen
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Large Screen",
    device = "spec:width=720dp,height=1280dp,dpi=320", // Custom large screen
    showBackground = true,
    showSystemUi = true
)
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
@Composable
fun ActionButtonPreviews() {
    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ActionButton(
            title = "Ingresar",
            onClick = { /* Do something */ },
            elevation = true,
            enabled = true
        )
    }
}
