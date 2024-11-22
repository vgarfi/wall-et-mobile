package com.example.wall_etmobile.screens.cashflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.CreditCard
data class CreditCardInfo(val bankName: String, val cardNumber: Number,val cardHolder: String,val cardExpiration: String,val cardImage: Int)

@Composable
fun PaymentSelector(
    cardsInfo: List<CreditCardInfo>,
    selectedObject: (CreditCardInfo) -> Unit, // Lambda to handle item selection
    modifier: Modifier = Modifier, // Modifier for customization
    PaymentBySelfBalance: Boolean = false,
    Header: @Composable () -> Unit
) {
    var selectedMethod by remember { mutableStateOf(cardsInfo.firstOrNull()) }
    val listState = rememberLazyListState()


    Column(modifier = modifier.padding(16.dp)) {
        Header()
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = cardsInfo) { card ->
                Box(
                    modifier = Modifier
                        .clickable {
                            // When a card is clicked, update the selectedMethod and notify the parent composable
                            selectedMethod = card
                            selectedObject(card)
                        }.height( 2020.dp)
                ){
                CreditCard(
                        bankName = card.bankName,
                        cardNumber = card.cardNumber,
                        cardHolder = card.cardHolder,
                        cardExpiration = card.cardExpiration,
                        cardImage = card.cardImage
                    )
                }

            }
        }
    }
}

@Preview(
    name = "Small Screen",
    device = "spec:width=320dp,height=640dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen landscape",
    device = "spec:height=320dp,width=640dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen",
    device = "spec:width=1500dp,height=800dp,dpi=320", // Custom small screen
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Small Screen landscape",
    device = "spec:height=1500dp,width=800dp,dpi=320", // Custom small screen
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PaymentSelectorPreview() {
    var selectedObject by remember { mutableStateOf<CreditCardInfo?>(null) }
    val cardsInfo = listOf(
        CreditCardInfo(
            bankName = "Galicia",
            cardNumber = 4509909098989898,
            cardHolder = "Tomas Borda",
            cardExpiration = "07/25",
            cardImage = R.drawable.purple_card
        ),
        CreditCardInfo(
            bankName = "Santander",
            cardNumber = 5234567812345678,
            cardHolder = "Lucia Fernandez",
            cardExpiration = "03/26",
            cardImage = R.drawable.red_card
        ),
        CreditCardInfo(
            bankName = "BBVA",
            cardNumber = 4111222233334444,
            cardHolder = "Marcos Diaz",
            cardExpiration = "12/24",
            cardImage = R.drawable.blue_card
        ),
        CreditCardInfo(
            bankName = "HSBC",
            cardNumber = 6011554445556666,
            cardHolder = "Ana Lopez",
            cardExpiration = "09/27",
            cardImage = R.drawable.green_card
        ),
        CreditCardInfo(
            bankName = "Macro",
            cardNumber = 378282246310005,
            cardHolder = "Pablo Rodriguez",
            cardExpiration = "05/28",
            cardImage = R.drawable.orange_card
        )
    )
    Column (modifier = Modifier.width(400.dp)) {
        PaymentSelector(cardsInfo = cardsInfo, selectedObject = { selectedObject = it }) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Metodo de Pago", fontWeight = FontWeight.Bold)
            }
        }
        selectedObject?.let {
            Text(
                text = "Selected card: ${it.bankName} - ${it.cardHolder}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
