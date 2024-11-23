package com.example.wall_etmobile.features.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.transactions.model.TransactionInfo

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentTransaction: TransactionInfo? = null,
    val transactions: List<TransactionInfo>? = null,
    var showCvu : Boolean = false,
    var showMoney : Boolean = true,
    var showFavoriteModal : Boolean = false,
    val error: Error? = null,
)