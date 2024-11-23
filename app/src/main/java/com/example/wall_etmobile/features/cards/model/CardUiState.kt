package com.example.wall_etmobile.features.cards.model

import com.example.wall_etmobile.core.model.Error

data class CardUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val cards: List<CreditCard>? = null,
    val currentCard: CreditCard? = null,
    val error: Error? = null
)

val CardUiState.canGetAllCards: Boolean get() = isAuthenticated
val CardUiState.canAddCard: Boolean get() = isAuthenticated
val CardUiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null