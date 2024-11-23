package com.example.wall_etmobile.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import com.example.wall_etmobile.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.*
import compose.icons.fontawesomeicons.regular.CreditCard
import compose.icons.fontawesomeicons.regular.ListAlt
import compose.icons.fontawesomeicons.solid.Qrcode


enum class Screen(val route: String, val label: Int, val icon: ImageVector, val tiny: Boolean,
                    val launchSingleTop: Boolean = true, val restoreState : Boolean = true, val saveState: Boolean = true) {
    HOME(route = "home", label = R.string.home, icon = Icons.Outlined.Home, tiny = false),
    TRANSACTIONS("transactions", label = R.string.transactions, icon = FontAwesomeIcons.Regular.ListAlt, tiny = true),
    CARDS("cards", label = R.string.cards, icon = FontAwesomeIcons.Regular.CreditCard, tiny = true),
    PROFILE("profile", label = R.string.profile, icon = Icons.Outlined.Person, tiny = false),
    // CashFlow
    TRANSFER("transfer", label = R.string.transfer_text, icon = Icons.Outlined.KeyboardArrowUp, tiny = false),
    CHARGE("charge", label = R.string.charge_text, icon = Icons.Outlined.MoreVert, tiny = false),
    ENTER("enter", label = R.string.income_text, icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    TRANSACTIONDETAILS("transaction-details", label = R.string.transaction_details, icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    ENTERFROM("enterFrom?source={source}&page={page}", label = R.string.enter_from, icon = Icons.Outlined.KeyboardArrowDown, tiny = false),
    TRANSFERTO("transferTo?target={target}&page={page}&contactName={contactName}&contactDetail={contactDetail}", label = R.string.transfer_to, icon = Icons.Outlined.KeyboardArrowUp, tiny = false),
    LOGIN("login", label = R.string.sign_in, icon = Icons.Outlined.Person, tiny = false),
    REGISTER("register", label = R.string.sign_up, icon = Icons.Outlined.Person, tiny = false),
    FORGOTPASSWORD("forgot-password", label = R.string.forgot_password, icon = Icons.Outlined.Person, tiny = false),
    VERIFYACCOUNT("verify-account", label = R.string.verify_your_account, icon = Icons.Outlined.Person, tiny = false),
    RESTOREPASSWORD("restore-password", label = R.string.restore_password, icon = Icons.Outlined.Person, tiny = false),
    SCANQR("scan-qr", label = R.string.scan_qr, icon = FontAwesomeIcons.Solid.Qrcode, tiny = false),
    SPLASH("splash", label = 0, icon = FontAwesomeIcons.Solid.Qrcode, tiny = false);

    companion object {
        val allScreens = listOf(HOME, TRANSACTIONS, CARDS, PROFILE, LOGIN, REGISTER, FORGOTPASSWORD, VERIFYACCOUNT, RESTOREPASSWORD, TRANSFER, CHARGE, ENTER, SCANQR, TRANSACTIONDETAILS, TRANSFERTO, ENTERFROM, SPLASH)
    }
}