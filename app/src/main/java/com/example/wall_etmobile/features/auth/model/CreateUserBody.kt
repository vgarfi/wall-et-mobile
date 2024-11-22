package com.example.wall_etmobile.features.auth.model

data class CreateUserBody(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: String,
)
