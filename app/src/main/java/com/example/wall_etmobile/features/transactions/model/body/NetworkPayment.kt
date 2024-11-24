package com.example.wall_etmobile.features.transactions.model.body

import kotlinx.serialization.Serializable

@Serializable
class NetworkPaymentBody (
    val amount: Double?,
    val description: String,
    val type: String,
    val receiverEmail: String?,
    val cardId: Int?,
)

enum class PaymentType(val label: String) {
    BALANCE("BALANCE"),
    CARD("CARD"),
    LINK("LINK")
}