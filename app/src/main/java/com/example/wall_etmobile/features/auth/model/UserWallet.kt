package com.example.wall_etmobile.features.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class UserWallet(
    val balance: Double,
    val cbu: String,
    val alias: String,
)
