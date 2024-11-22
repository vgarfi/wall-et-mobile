package com.example.wall_etmobile.design_kit.shared

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.LightPurple
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.core.theme.WalletMobileTheme

enum class TransactionType(val color: Color, val sign: Char , val description: String, @DrawableRes val iconId: Int) {
    INCOME(MainGreen, '+', "Income", iconId = R.drawable.enter_icon),
    CHARGE(MainGreen, '+', "Charge", iconId = R.drawable.charge_icon),
    TRANSFER(Color.Red, '-', "Transfer", iconId = R.drawable.transfer_icon)
}


@Composable
fun MovementTile(
    tileHeight: Dp,
    tileWidth: Dp? = null,
    @StringRes title: Int,
    titleSize: TextUnit,
    subTitle: String,
    subTitleSize: TextUnit,
    mount: Double,
    mountSize: TextUnit,
    transactionType: TransactionType,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable(onClick = { onClick() }),
        colors = CardDefaults.cardColors(MainWhite)
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
                .then(
                    if (tileWidth == null) Modifier.fillMaxWidth()
                        .height(tileHeight) else Modifier.size(tileWidth, tileHeight)
                )
        ) {
            Box(
                modifier = Modifier
                    .size(tileHeight)
                    .background(LightPurple, CircleShape)
                    .align(Alignment.CenterVertically)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = transactionType.iconId),
                    contentDescription = "Transaction Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    tint = MainPurple
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                Text(
                    text = stringResource(title),
                    fontSize = titleSize,
                    fontWeight = FontWeight.Normal,
                    color = MainBlack
                )
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.W300,
                    fontSize = subTitleSize,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${transactionType.sign}${"%.2f".format(mount)}",
                fontSize = mountSize,
                color = transactionType.color,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun MovementTileTransferPreview(){
    WalletMobileTheme {
        MovementTile(
            tileHeight = 80.dp,
            title = R.string.transfer_text,
            titleSize = 25.sp,
            subTitle = "19:12 a Tomas",
            subTitleSize = 12.sp,
            mount = 60.00,
            mountSize = 30.sp,
            transactionType = TransactionType.TRANSFER,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun MovementTileChargePreview(){
    WalletMobileTheme {
        MovementTile(
            tileHeight = 80.dp,
            title = R.string.charge_text,
            titleSize = 25.sp,
            subTitle = "19:12 a Tomas",
            subTitleSize = 12.sp,
            mount = 60.00,
            mountSize = 30.sp,
            transactionType = TransactionType.CHARGE,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun MovementTileIncomePreview(){
    WalletMobileTheme {
        MovementTile(
            tileHeight = 80.dp,
            title = R.string.income_text,
            titleSize = 25.sp,
            subTitle = "19:12 a Tomas",
            subTitleSize = 12.sp,
            mount = 60.00,
            mountSize = 30.sp,
            transactionType = TransactionType.INCOME,
            onClick = {}
        )
    }
}