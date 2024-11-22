package com.example.wall_etmobile.core.model

import com.example.wall_etmobile.features.auth.model.User
import com.example.wall_etmobile.features.cards.model.CreditCard
import java.util.Date

data class WalletState (
    val id: Int,
    val balance: Long,
    val cbu: String,
    val createdAt: String,
    val updatedAt: String,
)