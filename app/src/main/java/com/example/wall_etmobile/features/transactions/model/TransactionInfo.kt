package com.example.wall_etmobile.features.transactions.model

import com.example.wall_etmobile.features.auth.model.User2
import com.example.wall_etmobile.features.cards.model.Card

data class TransactionInfo(
    var id: Int,
    var type: TransactionType,
    var amount: Double,
    var createdAt: String?,
    var updatedAt: String?,
    var pending: Boolean,
    var linkUuid: String?,
    var card: TransactionCardInfo?,
    var payer: User2?,
    var receiver: User2?
)