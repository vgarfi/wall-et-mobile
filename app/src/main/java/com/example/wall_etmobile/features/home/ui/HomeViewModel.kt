package com.example.wall_etmobile.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.features.transactions.ui.TransactionUiState
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel

class HomeViewModel(
    sessionManager: SessionManager,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun toggleShowModalFavorite() {
        uiState = uiState.copy(showFavoriteModal = !uiState.showFavoriteModal)
    }

    fun toggleShowCvu() {
        uiState = uiState.copy(showCvu = !uiState.showCvu)
    }

    fun toggleShowMoney() {
        uiState = uiState.copy(showMoney = !uiState.showMoney)
    }

    companion object {
        const val TAG = "UI Layer"
        fun provideFactory(
            application: MyApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    application.sessionManager
                ) as T
            }
        }
    }
}