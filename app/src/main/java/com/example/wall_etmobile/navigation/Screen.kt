package com.example.wall_etmobile.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.*
import compose.icons.fontawesomeicons.regular.CreditCard
import compose.icons.fontawesomeicons.regular.ListAlt

enum class Screen(val route: String, val label: String, val icon: ImageVector, val tiny: Boolean,
                    val launchSingleTop: Boolean = true, val restoreState : Boolean = true, val saveState: Boolean = true) {
    HOME(route = "home", label = "Inicio", icon = Icons.Outlined.Home, tiny = false),
    TRANSACTIONS("transactions", label = "Movimientos", icon = FontAwesomeIcons.Regular.ListAlt, tiny = true),
    CARDS("cards", label = "Tarjetas", icon = FontAwesomeIcons.Regular.CreditCard, tiny = true),
    PROFILE("profile", label = "Perfil", icon = Icons.Outlined.Person, tiny = false),
    // CashFlow
    TRANSFER("transfer", label = "Transferir", icon = Icons.Outlined.KeyboardArrowUp, tiny = false),
    CHARGE("charge", label = "Cobrar", icon = Icons.Outlined.MoreVert, tiny = false),
    ENTER("enter", label = "Ingresar", icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    TRANSACTIONDETAILS("transaction-details", label = "Detalles de la transaccion", icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    ENTERFROM("enterFrom?source={source}&page={page}", label = "Ingresar", icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    TRANSFERTO("transferTo?target={target}&page={page}&contactName={contactName}&contactDetail={contactDetail}", label = "Transferir a ...", icon = Icons.Outlined.KeyboardArrowUp, tiny = false),
    WELCOME("welcome", label = "Bienvenido", icon = Icons.Outlined.Person, tiny = false),
    LOGIN("login", label = "Iniciá Sesión", icon = Icons.Outlined.Person, tiny = false),
    REGISTER("register", label = "Registrate", icon = Icons.Outlined.Person, tiny = false),
    FORGOTPASSWORD("forgot-password", label = "Olvidé mi contraseña", icon = Icons.Outlined.Person, tiny = false),
    VERIFYACCOUNT("verify-account", label = "Verificá tu cuenta", icon = Icons.Outlined.Person, tiny = false),
    RESTOREPASSWORD("restore-password", label = "Recuperá tu contraseña", icon = Icons.Outlined.Person, tiny = false);

    companion object {
        val allScreens = listOf(HOME, TRANSACTIONS, CARDS, PROFILE, WELCOME, LOGIN, REGISTER, FORGOTPASSWORD, VERIFYACCOUNT, RESTOREPASSWORD)
    }
}