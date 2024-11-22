package com.example.wall_etmobile.core.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainGreen
import com.example.wall_etmobile.design_kit.shared.TransactionType

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