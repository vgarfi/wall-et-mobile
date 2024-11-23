package com.example.wall_etmobile.features.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val wallet: UserWallet? = null,
)