package com.example.wall_etmobile.features.auth.viewmodel

import com.example.wall_etmobile.core.model.Error
import com.example.wall_etmobile.features.auth.model.User

data class AuthState(
    val isAuthenticated: Boolean = false,
    val hasVerifiedEmail: Boolean = false,
    val userRegisterEmail: String? = null,
    val userRegisterPassword: String? = null,
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: Error? = null
)
