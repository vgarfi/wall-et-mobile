package com.example.wall_etmobile.core.model

data class Payment (
    val amount: Long,
    val description: String,
    val type: PaymentType,
    val recieverEmail: String?,
    val cardId: Int?,
    val dateTime: String,
    val id: String
)

enum class PaymentType(val label: String) {
    BALANCE("BALANCE"),
    CARD("CARD"),
    LINK("LINK")
}