package com.example.wall_etmobile.features.cards.viewmodel

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
import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.cards.model.CardUiState
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.cards.repository.CardRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class CardViewModel(
    sessionManager: SessionManager,
    private val cardRepository: CardRepository,
) : ViewModel() {

    var uiCardState by mutableStateOf(CardUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set
    

    fun getCards() = runOnViewModelScope(
        { cardRepository.getCards(true) },
        { state, response -> state.copy(cards = response) }
    )

    fun addCard(card: CreditCard) = runOnViewModelScope(
        {
            cardRepository.addCard(card)
        },
        { state, response ->
            state.copy(
                currentCard = response,
                cards = null
            )
        }
    )

    fun deleteCard(cardId: Int) = runOnViewModelScope(
        { cardRepository.deleteCard(cardId) },
        { state, _ ->
            state.copy(
                currentCard = null,
                cards = null
            )
        }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (CardUiState, R) -> CardUiState
    ): Job = viewModelScope.launch {
        uiCardState = uiCardState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiCardState = updateState(uiCardState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiCardState = uiCardState.copy(isFetching = false, error = handleError(e))
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
                return CardViewModel(
                    application.sessionManager,
                    application.cardRepository) as T
            }
        }
    }
}