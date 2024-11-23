package com.example.wall_etmobile.features.auth.viewmodel

import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.auth.model.User

data class AuthState(
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: Error? = null
)
