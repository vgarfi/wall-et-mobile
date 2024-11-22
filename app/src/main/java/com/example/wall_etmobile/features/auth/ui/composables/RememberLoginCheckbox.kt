package com.example.wall_etmobile.features.auth.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite

@Composable
fun RememberLoginCheckbox() {
    var isChecked by remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = CheckboxDefaults.colors(
                checkmarkColor = MainWhite,
                uncheckedColor = MainPurple,
                checkedColor = MainPurple
            )
        )
        Text(
            text = stringResource(R.string.remember_me),
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
fun RememberLoginCheckboxPreview() {
    RememberLoginCheckbox()
}
