package com.example.wall_etmobile.features.home.ui.designKit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Copy
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.CopyCVU
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CvuBottomSheet(
    onDismissRequest: () -> Unit,
    cvu: String,
    username: String
) {
    var isSheetVisible by remember { mutableStateOf(true) }

    if (isSheetVisible) {
        ModalBottomSheet(
            containerColor = MainWhite,
            onDismissRequest = {
                isSheetVisible = false
                onDismissRequest()
            },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = stringResource(R.string.greeting),
                        fontSize = 25.sp,
                        color = MainBlack
                    )
                    Text(
                        text = " $username!",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainBlack
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = stringResource(R.string.cvu_presenter),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CopyCVU(
                    number = cvu
                )
            }
        }
    }
}
