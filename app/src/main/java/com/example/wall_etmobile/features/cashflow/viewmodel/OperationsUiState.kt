package com.example.wall_etmobile.features.cashflow.viewmodel

import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.transactions.model.TransactionType
import com.example.wall_etmobile.features.transactions.model.body.NetworkPaymentBody

data class OperationsUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    var currentAmount : String? = null,
    var currentPaymentMethod : CreditCard? = null,
    var currentReceiverID : String? = null,
    var currentMessage : String? = null,
    val error: Error? = null,
    val isNewOperation: Boolean = true,
    val operationType : TransactionType? = null,
    val payment : NetworkPaymentBody? = null,
)


