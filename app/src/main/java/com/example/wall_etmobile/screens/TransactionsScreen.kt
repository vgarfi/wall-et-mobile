package com.example.wall_etmobile.screens

import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import android.provider.CalendarContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.data.MovementData
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.example.wall_etmobile.design_kit.shared.MovementTileList
import com.example.wall_etmobile.design_kit.shared.TransactionType
import com.example.wall_etmobile.navigation.NavigatorWrapper
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.design_kit.shared.DateRangePickerModal
import com.example.wall_etmobile.design_kit.shared.SearchBar
import com.example.wall_etmobile.design_kit.transactions.FilterButton
import com.example.wall_etmobile.design_kit.transactions.FilterIndicator
import com.example.wall_etmobile.screens.cashflow.TransactionDetails
import com.example.wall_etmobile.ui.theme.MainWhite
import java.util.Locale


@Composable
fun TransactionsScreen(navWrapper: NavigatorWrapper, adaptiveInfo: WindowAdaptiveInfo) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val isRotated =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE  // si es verdadero esta rotada

    var tileHeight = (screenHeight * 0.065).dp
    var titleSize = (screenHeight * 0.025).sp
    var subTitleSize = (screenHeight * 0.015).sp
    var mountSize = (screenHeight * 0.03).sp
    var onMovementClick = { navWrapper.navigateToDetailsFromMovements() }

    val searchText = remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    var startDate by remember { mutableStateOf<Long?>(null) }
    var endDate by remember { mutableStateOf<Long?>(null) }

    var formatedStartDate by remember { mutableStateOf<String?>(null) }
    var formatedEndDate by remember { mutableStateOf<String?>(null) }


    val filterAction = {
        showDatePicker = true
    }

    var movements = listOf(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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
        ), MovementData(
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

    if (isRotated || adaptiveInfo.windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
        var showDetails by rememberSaveable { mutableStateOf(false) }

        tileHeight = (screenHeight * 0.15).dp
        titleSize = (screenHeight * 0.05).sp
        subTitleSize = (screenHeight * 0.04).sp
        mountSize = (screenHeight * 0.04).sp
        onMovementClick = { showDetails = true }

        movements = listOf(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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
            ), MovementData(
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .width((screenWidth * 0.5).dp)
                    .fillMaxHeight()
            ) {
                BaseScaffold(
                    tinyText = "",
                    bigText = stringResource(R.string.transactions)
                ) {
                    Column {
                        if (startDate != null && endDate != null && formatedStartDate != null && formatedEndDate != null) {
                            FilterIndicator(
                                formatedStartDate = formatedStartDate!!,
                                formatedEndDate = formatedEndDate!!,
                                onClick = {
                                    startDate = null
                                    endDate = null
                                    formatedStartDate = null
                                    formatedEndDate = null
                                }
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenHeight * 0.08).dp)

                    ) {
                        SearchBar(
                            width = (screenWidth * 0.5 * 0.6).dp,
                            searchText = searchText
                        )

                        FilterButton(
                            width = (screenWidth * 0.5 * 0.4).dp,
                            onClick = {
                                showDatePicker = true
                            }
                        )
                    }
                    MovementTileList(movements = movements)
                }
            }
            Box(
                modifier = Modifier
                    .width((screenWidth * 0.5).dp)
                    .background(MainWhite)
                    .padding(40.dp)
                    .align(Alignment.CenterVertically)
            ) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (showDetails){
                        TransactionDetails()
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Text(
                                text = stringResource(R.string.select_movent_indication),
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = MaterialTheme.typography.titleMedium.letterSpacing,
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                                    .wrapContentHeight(Alignment.CenterVertically),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    } else {
        BaseScaffold(
            tinyText = "",
            bigText = stringResource(R.string.transactions)
        ) {
            if (startDate != null && endDate != null && formatedStartDate != null && formatedEndDate != null) {
                FilterIndicator(
                    formatedStartDate = formatedStartDate!!,
                    formatedEndDate = formatedEndDate!!,
                    onClick = {
                        startDate = null
                        endDate = null
                        formatedStartDate = null
                        formatedEndDate = null
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.05).dp)
            ) {
                SearchBar(
                    width = (screenWidth * 0.6).dp,
                    searchText = searchText
                )

                FilterButton(
                    width = (screenWidth * 0.4).dp,
                    onClick = {
                        showDatePicker = true
                    }
                )
            }

            MovementTileList(movements = movements)
        }
        if (showDatePicker) {
            DateRangePickerModal(
                onDateRangeSelected = { pair: Pair<Long?, Long?> ->
                    if (pair.first != null && pair.second != null) {
                        startDate = pair.first
                        endDate = pair.second
                        formatedStartDate =
                            SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(startDate)
                        formatedEndDate =
                            SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(endDate)
                    }
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false },
                height = (screenHeight * 0.8).dp
            )
        }
    }
}