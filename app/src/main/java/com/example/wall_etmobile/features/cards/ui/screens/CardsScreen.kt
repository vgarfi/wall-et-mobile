package com.example.wall_etmobile.features.cards.ui.screens
import com.example.wall_etmobile.features.cards.ui.composables.AddNewCreditCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.BaseScaffold
import com.example.wall_etmobile.core.designKit.BigIconButton
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.ui.composables.CreditCardComponent
import com.example.wall_etmobile.features.cards.ui.composables.getBankFromCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import java.util.Date

@Composable
fun CardsScreen(
    viewModel: CardViewModel = viewModel(factory = CardViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiCardState

    var showDialog by remember { mutableStateOf(false) }
    val onAddCard: (String, String, String, String) -> Unit = { cardNumber, cardCVV, cardExpiration, cardHolder ->
        val newCreditCard  = CreditCard(
            number = cardNumber,
            cvv = cardCVV,
            expirationDate = cardExpiration,
            holderName = cardHolder,
            color = cardNumber.toLong().rem(6).toInt()
        )
        viewModel.addCard(newCreditCard)
        viewModel.getCards()
    }

    LaunchedEffect(Unit) {
        if (uiState.cards.isNullOrEmpty()) {
            viewModel.getCards()
        }
    }

    BaseScaffold(tinyText = "tus", bigText = "Tarjetas") {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ){
            BigIconButton(icon = R.drawable.add_card_icon, boldText = "Agregá", normalText ="una tarjeta", onClick = {
                showDialog = true
            })
            BigIconButton(icon = R.drawable.scan_card, boldText = "Escaneá", normalText ="una tarjeta", onClick = {} )
        }
        Box(modifier = Modifier.height(10.dp))

        Column (
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp)
                .padding(10.dp)
        ) {
            if (!uiState.cards.isNullOrEmpty()) {
                uiState.cards.forEach { card ->
                    CreditCardComponent(
                        bankName = getBankFromCard(card.number),
                        cardNumber = card.number,
                        cardHolder = card.holderName,
                        cardExpiration = card.expirationDate,
                        cardImageIndex = card.color
                    )
                    Box(modifier = Modifier.height(20.dp))
                }
            }

        }


        AddNewCreditCard(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onAddCard = onAddCard
        )
    }
}

@Preview
@Composable
fun CardsScreenPreview() {
    CardsScreen()
}