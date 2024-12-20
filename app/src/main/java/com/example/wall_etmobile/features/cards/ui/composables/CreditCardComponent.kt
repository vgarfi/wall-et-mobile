package com.example.wall_etmobile.features.cards.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite
import kotlinx.serialization.json.Json.Default.configuration

@Composable
fun CreditCardComponent(
    bankName: String,
    cardNumber: String,
    cardHolder: String,
    cardExpiration: String,
    cardImageIndex: Int,
    scaleFactor: Float = 1f,
    alpha : Float = 1f
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val cardImages = listOf(
        R.drawable.purple_card,
        R.drawable.blue_card,
        R.drawable.red_card,
        R.drawable.pink_card,
        R.drawable.green_card,
        R.drawable.orange_card,
        R.drawable.yellow_card,
    )
    Box() {
        Image(
            painter = painterResource(id = if(cardImageIndex > cardImages.size-1)  cardImages.elementAt(0) else cardImages.elementAt(cardImageIndex)),
            contentDescription = "card",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            alpha = alpha
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp)
                .padding(vertical = 5.dp, horizontal = 2.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = bankName, color = MainWhite, fontWeight = FontWeight.Bold)
                Box(modifier = Modifier.height(3.dp))
               if (cardNumber.isNotEmpty()) FormatCardNumber(cardNumber = cardNumber)
            }
            Box(modifier = Modifier.height((80*scaleFactor).dp))
            Row {
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (cardNumber.isNotEmpty()) Text(text = "Card Name", color = MainWhite, fontWeight = FontWeight.W300)
                    Box(modifier = Modifier.height(3.dp))
                    if (cardNumber.isNotEmpty())  Text(text = cardHolder, color = MainWhite, fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.width((50*scaleFactor).dp))
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (cardNumber.isNotEmpty()) Text(text = "Exp", color = MainWhite, fontWeight = FontWeight.W300)
                    Box(modifier = Modifier.height(3.dp))
                    if (cardNumber.isNotEmpty()) Text(text = cardExpiration, color = MainWhite, fontWeight = FontWeight.W500)
                }
            }
        }
    }

}

@Composable
fun FormatCardNumber(cardNumber: String) {
    val formattedNumber = if (cardNumber.length >= 16) {
        "**** **** **** " + cardNumber.takeLast(4)
    } else {
        "**** **** **** " + cardNumber.takeLast(minOf(4, cardNumber.length))
    }

    Text(text = formattedNumber, color = MainWhite)
}

@Composable
fun getCardIcon(cardNumber: String): @Composable (() -> Unit) {
    return when {
        cardNumber.startsWith("4") -> {
            {
                Icon(
                    painter = painterResource(id = com.example.wall_etmobile.R.drawable.visa),
                    contentDescription = "Visa",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        else -> {
            {
                Icon(
                    painter = painterResource(id = com.example.wall_etmobile.R.drawable.mastercard),
                    contentDescription = "MasterCard",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
//        cardNumber.startsWith("3") && (cardNumber[1] == '4' || cardNumber[1] == '7') -> {
//            {
//                Icon(
//                    painter = painterResource(id = com.example.wall_etmobile.R.drawable.amex),
//                    contentDescription = "American Express",
//                    modifier = Modifier.size(30.dp)
//                )
//            }
//        }
    }
}

fun isValidCardNumber(cardNumber: String): Boolean {
    return cardNumber.length == 16 && cardNumber.all { it.isDigit() }
}

fun getBankFromCard(cardNumber: String): String {
    if (cardNumber.length < 6) return ""

    val bin = cardNumber.substring(0, 6)

    val bankMap = mapOf(
        "450000" to "BNA",
        "450001" to "Santander",
        "450002" to "Galicia",
        "450003" to "Banco Provincia",
        "450004" to "BBVA",
        "450006" to "ICBC",
        "450007" to "Macro",
        "601100" to "Discover",
        "622126" to "UnionPay",
        "520000" to "HSBC",
        "411111" to "AMEX",
    )

    return bankMap[bin] ?: "HSBC"
}
