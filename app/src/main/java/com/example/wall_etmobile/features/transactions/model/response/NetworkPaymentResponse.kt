package com.example.wall_etmobile.features.transactions.model.response

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentResponse (
    val linkUuid: String?,
    val newBalance: Double?
)
