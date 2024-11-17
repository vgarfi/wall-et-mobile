package com.pathak.barberapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
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
    // CashFlow
    object Transfer : Screen("transfer", label = "Transferir", icon = Icons.Outlined.KeyboardArrowUp, tiny = false)
    object Charge: Screen("charge", label = "Cobrar", icon = Icons.Outlined.MoreVert, tiny = false)
    object Enter: Screen("enter", label = "Ingresar", icon = Icons.Outlined.KeyboardArrowDown, tiny = false)

}