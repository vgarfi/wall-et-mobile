package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.ui.composables.CreditCardComponent
import com.example.wall_etmobile.features.cards.ui.composables.getBankFromCard
import com.example.wall_etmobile.features.cashflow.ui.screens.getSampleCards

@Composable
fun PaymentSelector(
    cards: List<CreditCard>,
    selectedObject: (CreditCard) -> Unit,
    modifier: Modifier = Modifier,
    PaymentBySelfBalance: Boolean = false,
    Header: @Composable () -> Unit
) {
    var selectedMethod by remember { mutableStateOf(cards.firstOrNull()) }
    val listState = rememberLazyListState()


    Column(modifier = modifier.padding(16.dp)) {
        Header()
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = cards) { card ->
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedMethod = card
                            selectedObject(card)
                        }.height( 2020.dp)
                ){
                CreditCardComponent(
                        bankName = getBankFromCard(card.number),
                        cardNumber = card.number,
                        cardHolder = card.holderName,
                        cardExpiration = card.expirationDate,
                        cardImageIndex = card.color
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
    var selectedObject by remember { mutableStateOf<CreditCard?>(null) }
    getSampleCards()
    Column (modifier = Modifier.width(400.dp)) {
        PaymentSelector(cards = getSampleCards(), selectedObject = { selectedObject = it }) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Metodo de Pago", fontWeight = FontWeight.Bold)
            }
        }
        selectedObject?.let {
            Text(
                text = "Selected card: ${getBankFromCard(it.number)} - ${it.holderName}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
