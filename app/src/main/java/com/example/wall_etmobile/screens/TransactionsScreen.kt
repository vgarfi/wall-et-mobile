package com.example.wall_etmobile.screens

import CustomTextField
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.data.MovementData
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.example.wall_etmobile.design_kit.shared.MovementTileList
import com.example.wall_etmobile.design_kit.shared.TransactionType
import com.example.wall_etmobile.navigation.NavigatorWrapper
import androidx.compose.ui.res.stringResource
import com.example.wall_etmobile.design_kit.shared.DateRangePickerModal
import java.util.Locale


@Composable
fun TransactionsScreen(navWrapper: NavigatorWrapper, adaptiveInfo: WindowAdaptiveInfo) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val tileHeight = (screenHeight*0.065).dp
    val titleSize = (screenHeight*0.025).sp
    val subTitleSize = (screenHeight*0.015).sp
    val mountSize = (screenHeight*0.03).sp

    val onMovementClick = { navWrapper.navigateToDetailsFromMovements() }
    val searchText = remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    var startDate by remember { mutableStateOf<Long?>(null) }
    var endDate by remember { mutableStateOf<Long?>(null) }

    var formatedStartDate by remember { mutableStateOf<String?>(null) }
    var formatedEndDate by remember { mutableStateOf<String?>(null) }


    val filterAction = {
        showDatePicker = true
    }

    val movements = listOf(
        MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        ),MovementData(
            tileHeight = tileHeight,
            title = R.string.charge_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.CHARGE,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.transfer_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 60.00,
            mountSize = mountSize,
            transactionType = TransactionType.TRANSFER,
            onClick = onMovementClick
        ),
        MovementData(
            tileHeight = tileHeight,
            title = R.string.income_text,
            titleSize = titleSize,
            subTitle = "19:12 a Tomas",
            subTitleSize = subTitleSize,
            mount = 111.00,
            mountSize = mountSize,
            transactionType = TransactionType.INCOME,
            onClick = onMovementClick
        )
    )

    BaseScaffold(
        tinyText = "",
        bigText = stringResource(R.string.transactions)) {
        if (startDate != null && endDate != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$formatedStartDate - $formatedEndDate",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                IconButton(
                    onClick = {
                        startDate = null
                        endDate = null
                        formatedStartDate = null
                        formatedEndDate = null
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.cancel),
                        contentDescription = "quit-filter",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight*0.05).dp)
        ) {
            Box(
                modifier = Modifier
                    .width((screenWidth*0.6).dp)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp)
            ) {
                CustomTextField(
                    label = stringResource(R.string.search_label),
                    hint = "",
                    isPassword = false,
                    controller = searchText,
                    prefix = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search-icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }
            ElevatedButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width((screenWidth*0.4).dp)
                    .fillMaxHeight(),
                onClick = {
                    filterAction()
                },
                colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    contentDescription = "filter-icon",
                    imageVector = Icons.Filled.Menu,
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.filter),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        MovementTileList(movements = movements)
    }
    if (showDatePicker){
        DateRangePickerModal(
            onDateRangeSelected = { pair: Pair<Long?, Long?> ->
                startDate = pair.first
                endDate = pair.second
                formatedStartDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(startDate)
                formatedEndDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(endDate)
            },
            onDismiss = { showDatePicker = false },
            height = (screenHeight*0.8).dp
        )
    }
}