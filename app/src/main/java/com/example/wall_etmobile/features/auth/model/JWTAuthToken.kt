package com.example.wall_etmobile.features.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class JWTAuthToken(
    var token: String
)