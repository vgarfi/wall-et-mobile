package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.designKit.DashedCreditCard
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.ui.composables.AddNewCreditCard
import com.example.wall_etmobile.features.cards.ui.composables.CreditCardComponent
import com.example.wall_etmobile.features.cards.ui.composables.getBankFromCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.ui.screens.getSampleCards

@Composable
fun PaymentSelector(
    selectedObject: (CreditCard) -> Unit,
    modifier: Modifier = Modifier,
    PaymentBySelfBalance: Boolean = false,
    viewModel : CardViewModel =( LocalContext.current.applicationContext as MyApplication).cardsViewmodel,
    Header: @Composable () -> Unit
) {

    var cards = viewModel.uiCardState.cards.orEmpty()
    val selfBalancePayment = CreditCard(number = "0000 0000 0000 0000", holderName = "SELFBALANCE", color = 0, expirationDate = "00/00", cvv = null)
    var uiCards = cards
    var selectedCard by remember { mutableStateOf<CreditCard?>(null) }
    val listState = rememberLazyListState()
    var showDialog by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

    Column(modifier = modifier.padding(1.dp)) {
        Header()
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (PaymentBySelfBalance){
            item {
                Box(
                    modifier = Modifier.clickable {
                        selectedObject(selfBalancePayment)
                        selectedCard = selfBalancePayment
                    }
                ){
                    CreditCardComponent(
                        bankName = "Dinero en cuenta",
                        cardNumber = "",
                        cardHolder = "",
                        cardExpiration = "",
                        cardImageIndex = 0,
                        scaleFactor = 0.28f,
                        alpha = if(selectedCard == selfBalancePayment) 1f else 0.7f
                    )
                }
            }
            }
            items(items = uiCards) { card ->
                Box(
                    modifier = Modifier.clickable {
                        selectedObject(card)
                        selectedCard = card
                    }
                ) {
                    CreditCardComponent(
                        bankName = getBankFromCard(card.number),
                        cardNumber = card.number,
                        cardHolder = card.holderName,
                        cardExpiration = card.expirationDate,
                        cardImageIndex = card.color,
                        scaleFactor = 0.28f,
                        alpha = if(selectedCard == card) 1f else 0.7f
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier.clickable {
                        showDialog = true
                    }
                ) {
                    DashedCreditCard(scaleFactor = 0.77f)
                }
            }
        }
    }

    AddNewCreditCard(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onAddCard = { cardNumber, cardCVV, cardExpiration, cardHolder ->
            val newCreditCard = CreditCard(
                number = cardNumber,
                cvv = cardCVV,
                expirationDate = cardExpiration,
                holderName = cardHolder,
                color = cardNumber.toLong().rem(6).toInt()
            )
            viewModel.addCard(newCreditCard)
            viewModel.getCards()
            uiCards = uiCards.plus(newCreditCard)
        }
    )

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
    name = "Custom Small Screen",
    device = "spec:width=1500dp,height=800dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Custom Small Screen landscape",
    device = "spec:height=1500dp,width=800dp,dpi=320",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PaymentSelectorPreview() {
    var selectedObject by remember { mutableStateOf<CreditCard?>(null) }
    Column(modifier = Modifier.width(400.dp)) {
        PaymentSelector(
//            cards = getSampleCards(),
            selectedObject = { selectedObject = it }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Método de Pago", fontWeight = FontWeight.Bold)
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
