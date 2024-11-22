package com.example.wall_etmobile.core.model.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(
    val message: String
)