package com.example.wall_etmobile.design_kit.shared

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
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
    val containerColor = colorResource(id = containerColorRes)
    val contentColor = colorResource(id = contentColorRes)
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

    val configuration = LocalConfiguration.current
    val adjustedHeight = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        height * 1.2
    } else {
        height.toDouble()
    }.toInt()

    val adjustedWidth = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        width * 1.6
    } else {
        width.toDouble()
    }.toInt()

    val adjustedFontSize = if (adjustedWidth > 300) 18.sp else if (adjustedWidth > 150) 16.sp else 14.sp

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
                    .height(adjustedHeight.dp)
                    .width(adjustedWidth.dp)
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
                    .height(adjustedHeight.dp)
                    .width(adjustedWidth.dp),
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

@Preview(showBackground = true )
@Composable
fun ActionButtonPreviews() {
    Column {
        ActionButton(
            title = "Ingresar",
            onClick = { /* Do something */ },
            elevation = true,
            enabled = false
        )
    }
}
