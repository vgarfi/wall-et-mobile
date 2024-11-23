package com.example.wall_etmobile.features.transactions.model

import com.example.wall_etmobile.features.auth.model.User

data class TransactionInfo(
    var id: Int,
    var type: TransactionType,
    var amount: Double,
    var createdAt: String?,
    var updatedAt: String?,
    var pending: Boolean,
    var linkUuid: String?,
    var card: TransactionCardInfo?,
    var payer: User?,
    var receiver: User?
)