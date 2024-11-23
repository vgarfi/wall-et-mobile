package com.example.wall_etmobile.features.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserBody(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: String,
)
