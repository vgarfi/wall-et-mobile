package com.example.wall_etmobile.features.auth.viewmodel

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
import com.example.wall_etmobile.features.auth.repository.UserRepository
import com.example.wall_etmobile.core.model.Error
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
) : ViewModel() {

    var state by mutableStateOf(AuthState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, response -> state.copy(isAuthenticated = true, user = response) }
    )

    fun register(firstName: String, lastName: String, email: String, password: String) = runOnViewModelScope(
        { userRepository.register(firstName, lastName, email, password) },
        { state, response -> state }
    )

    fun verify(code: String) = runOnViewModelScope(
        { userRepository.verify(code) },
        { state, response -> state }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                user = null,
            )
        }
    )

//    fun getCurrentUser() = runOnViewModelScope(
//        { userRepository.getCurrentUser(state.user == null) },
//        { state, response -> state.copy(user = response) }
//    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (AuthState, R) -> AuthState
    ): Job = viewModelScope.launch {
        state = state.copy(isLoading = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            state = updateState(state, response).copy(isLoading = false)
        }.onFailure { e ->
            state = state.copy(isLoading = false, error = handleError(e))
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
                return AuthViewModel(
                    application.sessionManager,
                    application.userRepository
                ) as T
            }
        }
    }
}