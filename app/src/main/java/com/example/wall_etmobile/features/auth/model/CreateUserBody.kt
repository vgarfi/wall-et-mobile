package com.example.wall_etmobile.features.auth.model

import java.text.SimpleDateFormat
import java.util.Locale

class CreateUserBody(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: String,
)
