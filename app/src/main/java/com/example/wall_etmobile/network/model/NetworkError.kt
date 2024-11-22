package com.example.wall_etmobile.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(
    val message: String
)