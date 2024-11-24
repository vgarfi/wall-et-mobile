package com.example.wall_etmobile.features.auth.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainRed
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun TermsCheckbox(
    onTap: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    var showTerms by remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { value ->
                isChecked = value
                onTap(value)
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = MainWhite,
                uncheckedColor = MainPurple,
                checkedColor = MainPurple
            )
        )
        Text(
            text = stringResource(R.string.i_accept_the),
            fontSize = 12.sp,
        )
        Text(
            text = stringResource(R.string.terms_and_conditions),
            fontSize = 12.sp,
            color = MainPurple,
            modifier = Modifier.clickable {
                showTerms = true
            }
        )
    }
    if (showTerms) {
        AlertDialog(
            containerColor = MainWhite,
            onDismissRequest = {
                showTerms = false
            },
            title = {
                Text(text = stringResource(R.string.terms_title), fontWeight = FontWeight.W400, color = MainBlack, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            },
            text = {
                Text(text = stringResource(R.string.terms_content), fontSize = 16.sp)
            },
            confirmButton = {
                androidx.compose.material3.TextButton(onClick = {
                    showTerms = false
                }) {
                    Text(text = stringResource(R.string.ok), color = MainPurple)
                }
            },
        )
    }

}
