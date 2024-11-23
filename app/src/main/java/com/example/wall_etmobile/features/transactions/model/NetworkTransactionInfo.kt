package com.example.wall_etmobile.features.transactions.model

import com.example.wall_etmobile.features.auth.model.User
import kotlinx.serialization.Serializable

@Serializable
class NetworkListTransactionInfo(
    var payments: Array<NetworkTransactionInfo>,
){
    fun asModel(): List<TransactionInfo> {
        return payments.map { it.asModel() }
    }
}

@Serializable
class NetworkTransactionInfo (
    var id: Int,
    var type: String,
    var card: NetworkTransactionCardInfo? = null,
    var amount: Double,
    var pending: Boolean,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var linkUuid: String? = null,
    var payer: User? = null,
    var receiver: User? = null
) {
    fun asModel(): TransactionInfo {
        return TransactionInfo(
            id = id,
            type = when {
                linkUuid != null -> TransactionType.CHARGE
                type == "CARD" && payer?.id == receiver?.id -> TransactionType.INCOME
                else -> TransactionType.TRANSFER
            },
            amount = amount,
            createdAt = createdAt,
            updatedAt = updatedAt,
            linkUuid = linkUuid,
            payer = payer,
            receiver = receiver,
            pending = pending,
            card = card?.asModel()
        )
    }
}
