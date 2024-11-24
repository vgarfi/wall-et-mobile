package com.example.wall_etmobile.features.transactions.ui.screens

import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.wall_etmobile.core.data.MovementData
import com.example.wall_etmobile.core.designKit.BaseScaffold
import com.example.wall_etmobile.core.designKit.MovementTileList
import com.example.wall_etmobile.core.designKit.TransactionTypeStyle
import com.example.wall_etmobile.core.navigation.NavigatorWrapper
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.core.designKit.DateRangePickerModal
import com.example.wall_etmobile.core.designKit.SearchBar
import com.example.wall_etmobile.features.transactions.ui.designKit.FilterButton
import com.example.wall_etmobile.features.transactions.ui.designKit.FilterIndicator
import com.example.wall_etmobile.features.cashflow.ui.composables.TransactionDetails
import com.example.wall_etmobile.core.theme.MainWhite
import java.util.Locale
import com.example.wall_etmobile.core.designKit.DoneTransactionDetails
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel


@Composable
fun TransactionsScreen(
    navWrapper: NavigatorWrapper,
    adaptiveInfo: WindowAdaptiveInfo,
    viewModel: TransactionViewModel
) {
    val uiState = viewModel.uiState

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val isRotated =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE  // si es verdadero esta rotada

    var movementTileHeight by remember { mutableStateOf((screenHeight * 0.065).dp) }
    var movementTitleSize by remember { mutableStateOf((screenHeight * 0.025).sp) }
    var movementSubTitleSize by remember { mutableStateOf((screenHeight * 0.015).sp) }
    var movementMountSize by remember { mutableStateOf((screenHeight * 0.03).sp) }
    var onMovementClick by remember { mutableStateOf<(Int) -> Unit>({}) }
    var clickable by remember { mutableStateOf(false) }

    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    var formatedStartDate by rememberSaveable { mutableStateOf<String?>(null) }
    var formatedEndDate by rememberSaveable { mutableStateOf<String?>(null) }


    val filterAction = {
        showDatePicker = true
    }

    val transactionsStyle: List<MovementData> = uiState.filteredTransactions?.map {
        val type: TransactionTypeStyle
        val subTitle: String
        when (it.type.description) {
            TransactionTypeStyle.CHARGE.description -> {
                type = TransactionTypeStyle.CHARGE
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.from) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
            TransactionTypeStyle.TRANSFER.description -> {
                type = TransactionTypeStyle.TRANSFER
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.to) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
            else -> {
                type = TransactionTypeStyle.INCOME
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.to) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
        }

        MovementData(
            id = it.id,
            tileHeight = movementTileHeight,
            title = stringResource(it.type.description),
            titleSize = movementTitleSize,
            subTitle = subTitle,
            subTitleSize = movementSubTitleSize,
            mount = it.amount,
            mountSize = movementMountSize,
            transactionTypeStyle = type,
            onClick = onMovementClick,
            clickable = clickable
        )
    } ?: emptyList()


    if (isRotated || adaptiveInfo.windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
        var showDetails by rememberSaveable { mutableStateOf(false) }

        movementTileHeight = (screenHeight * 0.15).dp
        movementTitleSize = (screenHeight * 0.05).sp
        movementSubTitleSize = (screenHeight * 0.04).sp
        movementMountSize = (screenHeight * 0.04).sp
        onMovementClick = { id: Int ->
            showDetails = true
            viewModel.setCurrentTransaction(id)
        }
        clickable = true

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
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenHeight * 0.08).dp)

                    ) {
                        if (uiState.startDate != null && uiState.endDate != null && formatedStartDate != null && formatedEndDate != null) {
                            FilterIndicator(
                                formatedStartDate = formatedStartDate!!,
                                formatedEndDate = formatedEndDate!!,
                                onClick = {
                                    viewModel.setFilterDate(null, null)
                                    formatedStartDate = null
                                    formatedEndDate = null
                                }
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                        }

                        FilterButton(
                            width = (screenWidth * 0.5 * 0.4).dp,
                            onClick = filterAction
                        )
                    }
                    if (uiState.completedTransactions != null) {
                        MovementTileList(movements = transactionsStyle)
                    } else {
                        viewModel.getTransactions()
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width((screenWidth * 0.5).dp)
                    .background(MainWhite)
                    .padding(30.dp)
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
                    if (showDetails && uiState.currentTransaction != null){
                        Box(modifier = Modifier.fillMaxSize()){
                            DoneTransactionDetails(uiState.currentTransaction)
                        }
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
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.05).dp)
            ) {
                if (uiState.startDate != null && uiState.endDate != null && formatedStartDate != null && formatedEndDate != null) {
                    FilterIndicator(
                        formatedStartDate = formatedStartDate!!,
                        formatedEndDate = formatedEndDate!!,
                        onClick = {
                            viewModel.setFilterDate(null, null)
                            formatedStartDate = null
                            formatedEndDate = null
                        }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                FilterButton(
                    width = (screenWidth * 0.4).dp,
                    onClick = filterAction
                )
            }
            if (uiState.completedTransactions != null) {
                MovementTileList(movements = transactionsStyle)
            } else {
                viewModel.getTransactions()
            }
        }
    }
    if (showDatePicker) {
        DateRangePickerModal(
            onDateRangeSelected = { pair: Pair<Long?, Long?> ->
                if (pair.first != null && pair.second != null) {
                    viewModel.setFilterDate(pair.first, pair.second)
                    formatedStartDate =
                        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(pair.first)
                    formatedEndDate =
                        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(pair.second)
                }
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
            height = (screenHeight * 0.8).dp
        )
    }
}