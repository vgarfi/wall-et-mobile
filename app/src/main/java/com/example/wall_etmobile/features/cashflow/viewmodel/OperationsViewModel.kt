package com.example.wall_etmobile.features.cashflow.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.core.datasource.DataSourceException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cashflow.repository.OperationsRepository
import com.example.wall_etmobile.features.transactions.model.TransactionType


class OperationsViewModel(
    sessionManager: SessionManager,
    private val operationRepository: OperationsRepository,

) : ViewModel() {
    var uiState by mutableStateOf(OperationsUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    init {
        fetchInitialData()
    }

    private fun fetchInitialData() {
        viewModelScope.launch {
            uiState = uiState.copy(
                currentAmount = operationRepository.getAmount(),
                currentReceiverID = operationRepository.getReceiverID(),
                currentMessage = operationRepository.getDescription(),
                currentPaymentMethod = operationRepository.getSelectedMethodPayment(),
                operationType = operationRepository.getOperationType(),
            )
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            setOperationType(null)
            setAmount(null)
            setReceiverID(null)
            setDescription(null)
            setPaymentMethod(null)
            uiState = uiState.copy(
               payment = null
            )

        }
    }


    fun getAmount() = runOnViewModelScope(
        { operationRepository.getAmount() },
        { state, response -> state.copy(currentAmount = response)}
    )

   fun setAmount(amount: String?) = runOnViewModelScope(
        { operationRepository.setAmount(amount) },
        { state, response -> state.copy(currentAmount = response)}
    )

    fun getReceiverID() = runOnViewModelScope(
        { operationRepository.getReceiverID() },
        { state, response -> state.copy(currentReceiverID = response)}
    )

    fun setReceiverID(id: String?) = runOnViewModelScope(
        { operationRepository.setReceiverID(id) },
        { state, response -> state.copy(currentReceiverID = response)}
    )

    fun getDescription() = runOnViewModelScope(
        { operationRepository.getDescription() },
        { state, response -> state.copy(currentMessage = response)}
    )

    fun setDescription(description: String?) = runOnViewModelScope(
        { operationRepository.setDescription(description) },
        { state, response -> state.copy(currentMessage = response)}
    )

    fun setPaymentMethod(paymentMethod: CreditCard?) = runOnViewModelScope(
        { operationRepository.setSelectedMethodPayment(paymentMethod) },
        { state, response -> state.copy(currentPaymentMethod = response)}
    )

    fun getPaymentMethod() = runOnViewModelScope(
        { operationRepository.getSelectedMethodPayment() },
        { state, response -> state.copy(currentPaymentMethod = response)}
    )

    fun setOperationType(operationType: TransactionType?) = runOnViewModelScope(
        { operationRepository.setOperationType(operationType) },
        { state, response -> state.copy(operationType = response)}
    )

    fun getOperationType() = runOnViewModelScope(
        { operationRepository.getOperationType() },
        { state, response -> state.copy(operationType = response)}
    )

    fun makePayment(context: Context) = runOnViewModelScope(
        { operationRepository.makePayment(context) },
        { state, response -> state.copy(payment = response)}
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (OperationsUiState, R) -> OperationsUiState
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



    companion object {
        const val TAG = "UI Layer"
        fun provideFactory(
            application: MyApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OperationsViewModel(
                    application.sessionManager,
                    application.operationsRepository,
                ) as T
            }
        }
    }
}