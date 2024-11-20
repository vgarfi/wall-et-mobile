package com.example.wall_etmobile.screens.auth.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.ui.theme.MainGrey
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite

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
            text = "Recordar inicio de sesión",
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
fun RememberLoginCheckboxPreview() {
    RememberLoginCheckbox()
}
