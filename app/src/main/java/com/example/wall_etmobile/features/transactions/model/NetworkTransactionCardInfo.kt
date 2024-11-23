package com.example.wall_etmobile.features.transactions.model

import kotlinx.serialization.Serializable

@Serializable
class NetworkTransactionCardInfo (
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String
) {
    fun asModel(): TransactionCardInfo{
        return TransactionCardInfo(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            type = type
        )
    }
}