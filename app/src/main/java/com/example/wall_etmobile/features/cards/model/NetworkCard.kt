package com.example.wall_etmobile.features.cards.model

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkCard(
    var id: Int?,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var cvv: String? = null,
    var type: String,
    var createdAt: String?,
    var updatedAt: String?
) {
    fun asModel(): CreditCard {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return CreditCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            holderName = fullName,
            cvv = cvv,
            createdAt = createdAt?.let { dateFormat.parse(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.parse(updatedAt!!) }
        )
    }
}