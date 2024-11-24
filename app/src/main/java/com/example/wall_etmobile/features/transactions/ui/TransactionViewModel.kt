package com.example.wall_etmobile.features.transactions.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.core.datasource.DataSourceException
import com.example.wall_etmobile.features.transactions.repository.TransactionRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.transactions.model.TransactionInfo
import com.example.wall_etmobile.features.transactions.model.body.NetworkPaymentBody
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TransactionViewModel(
    sessionManager: SessionManager,
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    var uiState by mutableStateOf(TransactionUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun getTransactions() = runOnViewModelScope(
        { transactionRepository.getTransactions(true) },
        { state, response -> state.copy(
            allTransactions = response,
            filteredTransactions = getFilteredTransactions(state),
            completedTransactions = getCompletedTransactions(state)
        )}
    )

    fun setFilterDate(startDate: Long?, endDate: Long?) {
        uiState.startDate = startDate
        uiState.endDate = endDate
        fetchFilteredTransactions(uiState)
    }

    fun addPayment(payment: NetworkPaymentBody) = runOnViewModelScope(
        { transactionRepository.addPayment(payment) },
        { state, response -> (state)}
    )



    private fun fetchFilteredTransactions(state: TransactionUiState){
        uiState = uiState.copy(filteredTransactions = getFilteredTransactions(state))
    }

    private fun getFilteredTransactions(state: TransactionUiState): List<TransactionInfo>? {
        val auxStateStartDate = state.startDate
        val auxStateEndDate = state.endDate
        return if (auxStateEndDate != null && auxStateStartDate != null) {
            state.completedTransactions?.filter { transaction ->
                val transactionTimestamp = transaction.createdAt?.let { dateToMillis(it) } ?: 0
                transactionTimestamp in auxStateStartDate..auxStateEndDate
            }
        } else {
            state.completedTransactions
        }
    }

    private fun fetchCompletedTransactions(state: TransactionUiState){
         uiState = uiState.copy(completedTransactions = getCompletedTransactions(state))
    }

    private fun getCompletedTransactions(state: TransactionUiState): List<TransactionInfo>? {
        return state.allTransactions?.filter { transaction ->
            !transaction.pending
        }
    }


    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (TransactionUiState, R) -> TransactionUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        }
    }

    private fun dateToMillis(dateString: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val localDate = LocalDate.parse(dateString, formatter)

        return localDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    }

    fun setCurrentTransaction(id: Int) {
        uiState = uiState.copy(currentTransaction = uiState.allTransactions?.find { it.id == id })
    }

    companion object {
        const val TAG = "UI Layer"
        fun provideFactory(
            application: MyApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TransactionViewModel(
                    application.sessionManager,
                    application.transactionRepository,
                    ) as T
            }
        }
    }
}