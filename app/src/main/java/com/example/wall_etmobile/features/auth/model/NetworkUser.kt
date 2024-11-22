package com.example.wall_etmobile.features.auth.model

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkUser(

    var id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: String
) {
    fun asModel(): User2 {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return User2(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = dateFormat.parse(birthDate)!!
        )
    }
}