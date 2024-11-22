package com.example.wall_etmobile.features.cards.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreditCard(
    var id: Int? = null,
    var number: String,
    var expirationDate: String,
    var holderName: String,
    var cvv: String?,
    var createdAt: Date? = null,
    var updatedAt: Date? = null
) {
    fun asNetworkModel(): NetworkCard {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return NetworkCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = holderName,
            cvv = cvv,
            type = "CREDIT",
            createdAt = createdAt?.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt!!) }
        )
    }
}