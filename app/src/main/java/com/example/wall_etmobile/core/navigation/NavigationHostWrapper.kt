package com.example.wall_etmobile.core.navigation

import com.example.wall_etmobile.features.cashflow.ui.screens.TransferToScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wall_etmobile.navigateToScreen
import com.example.wall_etmobile.features.cards.ui.screens.CardsScreen
import com.example.wall_etmobile.features.home.ui.screens.HomeScreen
import com.example.wall_etmobile.features.profile.ui.screens.ProfileScreen
import com.example.wall_etmobile.features.transactions.ui.screens.TransactionsScreen
import com.example.wall_etmobile.features.auth.ui.screens.ForgotPasswordScreen
import com.example.wall_etmobile.features.auth.ui.screens.LoginScreen
import com.example.wall_etmobile.features.auth.ui.screens.RegisterScreen
import com.example.wall_etmobile.features.auth.ui.screens.RestorePasswordScreen
import com.example.wall_etmobile.features.auth.ui.screens.VerifyAccountScreen
import com.example.wall_etmobile.features.auth.ui.screens.WelcomeScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.ChargeScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.EnterFromScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.EnterScreen
import com.example.wall_etmobile.features.cashflow.ui.composables.TransactionDetailsScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.TransferScreen
import com.example.wall_etmobile.features.qr_scanner.ui.screens.QRScannerScreen

@Composable
fun NavigationHostWrapper (
    navController: NavHostController,
    adaptiveInfo: WindowAdaptiveInfo,
    navigatorWrapper: NavigatorWrapper,
){
    NavHost(navController = navController,
        startDestination = Screen.HOME.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(Screen.SCANQR.route) {
            QRScannerScreen()
        }
        composable(Screen.HOME.route) {
            HomeScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo)
        }
        composable(Screen.TRANSACTIONS.route) {
            TransactionsScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo)
        }
        composable(Screen.CARDS.route) {
            CardsScreen()
        }
        composable(Screen.PROFILE.route) {
            ProfileScreen(navController)
        }
        composable(Screen.WELCOME.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.LOGIN.route) {
            LoginScreen(navController)
        }
        composable(Screen.REGISTER.route) {
            RegisterScreen(navController)
        }
        composable(Screen.FORGOTPASSWORD.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Screen.VERIFYACCOUNT.route) {
            VerifyAccountScreen(navController)
        }
        composable(Screen.RESTOREPASSWORD.route) {
            RestorePasswordScreen(navController)
        }
        composable(Screen.TRANSFER.route) {
            TransferScreen(
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) }
            )
        }
        composable(Screen.CHARGE.route) {
            ChargeScreen(navController = navController, navigateToScreen = { route, args -> navigateToScreen(navController, route, args) })
        }
        composable(Screen.ENTER.route) {
            EnterScreen(
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) }
            )
        }
        composable(
            Screen.ENTERFROM.route,
            arguments = listOf(navArgument("source") { type = NavType.StringType; nullable = true },
                navArgument("page") { type = NavType.StringType; nullable = true })
        ) {
                backStackEntry ->
            val source = backStackEntry.arguments?.getString("source")
            val page = backStackEntry.arguments?.getString("page")

            EnterFromScreen(
                source = source,
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                page = page?.toInt(),
            )
        }
        composable(Screen.TRANSACTIONDETAILS.route) {
            TransactionDetailsScreen(navController = navController, navigateToScreen = { route, args -> navigateToScreen(navController, route, args) })
        }
        composable(
            Screen.TRANSFERTO.route,
            arguments = listOf(navArgument("target") { type = NavType.StringType; nullable = true },
                navArgument("page") { type = NavType.StringType; nullable = true })
        ) {
                backStackEntry ->
            val target = backStackEntry.arguments?.getString("target")
            val page = backStackEntry.arguments?.getString("page")
            val contactName = backStackEntry.arguments?.getString("contactName")
            val contactDetail = backStackEntry.arguments?.getString("contactDetail")

            TransferToScreen(
                target = target,
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                page = page?.toInt(),
                contactName = contactName,
                contactDetail = contactDetail
            )
        }
    }
}