package com.pathak.barberapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.*
import compose.icons.fontawesomeicons.regular.CreditCard
import compose.icons.fontawesomeicons.regular.ListAlt

sealed class Screen(val route: String, val label: String, val icon: ImageVector, val tiny: Boolean) {
    object Home : Screen(route = "home", label = "Inicio", icon = Icons.Outlined.Home, tiny = false)
    object Transactions : Screen("transactions", label = "Movimientos", icon = FontAwesomeIcons.Regular.ListAlt, tiny = true)
    object Cards : Screen("cards", label = "Tarjetas", icon = FontAwesomeIcons.Regular.CreditCard, tiny = true)
    object Profile : Screen("profile", label = "Perfil", icon = Icons.Outlined.Person, tiny = false)
    object Welcome : Screen("welcome", label = "Bienvenido", icon = Icons.Outlined.Person, tiny = false)
    object Login : Screen("login", label = "Iniciá Sesión", icon = Icons.Outlined.Person, tiny = false)
    object Register : Screen("register", label = "Registrate", icon = Icons.Outlined.Person, tiny = false)
    object ForgotPassword : Screen("forgot-password", label = "Olvidé mi contraseña", icon = Icons.Outlined.Person, tiny = false)
    object VerifyAccount : Screen("verify-account", label = "Verificá tu cuenta", icon = Icons.Outlined.Person, tiny = false)
    object RestorePassword : Screen("restore-password", label = "Recuperá tu contraseña", icon = Icons.Outlined.Person, tiny = false)

    companion object {
        val allScreens = listOf(Home, Transactions, Cards, Profile, Welcome, Login, Register, ForgotPassword, VerifyAccount, RestorePassword)
    }
}