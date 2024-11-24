package com.example.wall_etmobile.features.cards.ui.screens
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import com.example.wall_etmobile.features.cards.ui.composables.AddNewCreditCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.BaseScaffold
import com.example.wall_etmobile.core.designKit.BigIconButton
import com.example.wall_etmobile.core.designKit.CustomCard
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainRed
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.ui.composables.CreditCardComponent
import com.example.wall_etmobile.features.cards.ui.composables.getBankFromCard
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import kotlinx.coroutines.delay
import java.util.Date
@Composable
fun CardsScreen(
    viewModel : CardViewModel =( LocalContext.current.applicationContext as MyApplication).cardsViewmodel
) {
    val uiState = viewModel.uiCardState

    var showDialog by remember { mutableStateOf(false) }
    val onAddCard: (String, String, String, String) -> Unit = { cardNumber, cardCVV, cardExpiration, cardHolder ->
        val newCreditCard = CreditCard(
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
            viewModel.getCards()
    }

    BaseScaffold(tinyText = stringResource(R.string.yours), bigText = stringResource(R.string.cards)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
            BigIconButton(
                icon = R.drawable.add_card_icon,
                boldText = stringResource(R.string.add),
                normalText = stringResource(R.string.a_new_card),
                onClick = { showDialog = true },
            )
            Box(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                if (uiState.cards.isNullOrEmpty()) {
                    item {
                        CustomCard (
                            isDashed = true,
                            modifier = Modifier.fillMaxHeight().padding(vertical = 20.dp),
                            backgroundColor = MainWhite
                        ){
                            Text(
                                stringResource(R.string.no_cards),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W400,
                                color = MainGrey.copy(alpha = 0.7f),
                                modifier = Modifier.fillMaxSize().padding(vertical = 30.dp)
                            )
                        }
                    }
                }
                else {
                    items(
                        items = uiState.cards.orEmpty(),
                        key = { card -> card.number }
                    ) { card ->
                        Box(
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) {
                            SwipeToDeleteContainer(
                                item = card,
                                onDelete = { cardToDelete ->
                                    if (cardToDelete.id != null) {
                                        viewModel.deleteCard(cardToDelete.id!!)
                                        viewModel.getCards()
                                    }
                                }
                            ) { currentCard ->
                                CreditCardComponent(
                                    bankName = getBankFromCard(currentCard.number),
                                    cardNumber = currentCard.number,
                                    cardHolder = currentCard.holderName,
                                    cardExpiration = currentCard.expirationDate,
                                    cardImageIndex = currentCard.color
                                )
                                Box(Modifier.height(20.dp))
                            }
                        }
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
}
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if ((value == SwipeToDismissBoxValue.StartToEnd || value == SwipeToDismissBoxValue.EndToStart)) {
                showDialog = true
                false
            } else {
                false
            }
        }
    )

    if (showDialog) {
        ConfirmationDialog(
            onConfirm = {
                isRemoved = true
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = {
                DeleteBackground(swipeDismissState = state)
            },
            content = { content(item) }
        )
    }
}

@Composable
fun DeleteBackground(
    swipeDismissState: SwipeToDismissBoxState
) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd || swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        MainRed
    } else {
        Color.Transparent
    }

    val alignment = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) Alignment.CenterStart else Alignment.CenterEnd

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = alignment
    ) {
        if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd || swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        containerColor = MainWhite,

        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Eliminar tarjeta", fontWeight = FontWeight.W400, color = MainBlack, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        },
        text = {
            Text(text = "¿Estás seguro de que quieres eliminar esta tarjeta? Esta acción no se puede deshacer, y deberás volver a ingresar sus datos para tenerla nuevamente en Wall-et", fontSize = 16.sp)
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = { onConfirm() }) {
                Text(text = "Eliminar", color = MainRed)
            }
        },

        dismissButton = {
            androidx.compose.material3.TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancelar")
            }
        }
    )
}

