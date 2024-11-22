package com.example.wall_etmobile.features.cards.ui.screens
import AddNewCreditCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.shared.BaseScaffold
import com.example.wall_etmobile.design_kit.shared.BigIconButton
import com.example.wall_etmobile.design_kit.shared.CreditCard

@Composable
fun CardsScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val onAddCard: (String, String, String) -> Unit = { cardNumber, expirationDate, bankName ->
        println("Tarjeta añadida: $cardNumber, $expirationDate, $bankName")
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
        Column (
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp)
                .padding(10.dp)
        ) {
            CreditCard(
                bankName = "CBB Bank",
                cardNumber = 1287987854323079,
                cardHolder = "Valentin Garfi",
                cardExpiration = "07/27",
                cardImage = R.drawable.purple_card
            )
            Box(modifier = Modifier.height(20.dp))
            CreditCard(
                bankName = "HSBC Bank",
                cardNumber = 1287987854322345,
                cardHolder = "Lautaro Paletta",
                cardExpiration = "05/29",
                cardImage = R.drawable.green_card
            )
            Box(modifier = Modifier.height(20.dp))
            CreditCard(
                bankName = "ISBC",
                cardNumber = 1287987854329090,
                cardHolder = "Agustín Ronda",
                cardExpiration = "01/25",
                cardImage = R.drawable.red_card
            )
            Box(modifier = Modifier.height(20.dp))
            CreditCard(
                bankName = "Macro",
                cardNumber = 1287987854321237,
                cardHolder = "Tomás Borda",
                cardExpiration = "12/26",
                cardImage = R.drawable.blue_card
            )
            Box(modifier = Modifier.height(100.dp))
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