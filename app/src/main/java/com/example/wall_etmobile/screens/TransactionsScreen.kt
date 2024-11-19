package com.example.wall_etmobile.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.data.MovementData
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.example.wall_etmobile.design_kit.shared.MovementTileList
import com.example.wall_etmobile.design_kit.shared.TransactionType


@Composable
fun TransactionsScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val tileHeight = (screenHeight*0.065).dp
    val titleSize = (screenHeight*0.025).sp
    val subTitleSize = (screenHeight*0.015).sp
    val mountSize = (screenHeight*0.03).sp
    val onMovementClick = {  }

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

    BaseScaffold(tinyText = "tus", bigText = "Movimientos") {
        MovementTileList(movements = movements)
    }
}

@Preview
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen()
}