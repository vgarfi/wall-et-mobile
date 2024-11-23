package com.example.wall_etmobile.features.transactions.ui

import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.transactions.model.TransactionInfo

data class TransactionUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentTransaction: TransactionInfo? = null,
    val transactions: List<TransactionInfo>? = null,
    val filteredTransactions: List<TransactionInfo>? = null,
    val error: Error? = null,
    var startDate: Long? = null,
    var endDate: Long? = null
) {

}


